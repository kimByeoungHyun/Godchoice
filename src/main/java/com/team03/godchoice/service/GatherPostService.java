package com.team03.godchoice.service;

import com.team03.godchoice.domain.Member;
import com.team03.godchoice.dto.responseDto.CreateResDto;
import com.team03.godchoice.enumclass.RegionTag;
import com.team03.godchoice.domain.gatherPost.GatherPost;
import com.team03.godchoice.domain.gatherPost.GatherPostComment;
import com.team03.godchoice.domain.gatherPost.GatherPostImg;
import com.team03.godchoice.dto.GlobalResDto;
import com.team03.godchoice.dto.requestDto.gatherpostDto.GatherPostRequestDto;
import com.team03.godchoice.dto.requestDto.gatherpostDto.GatherPostUpdateDto;
import com.team03.godchoice.dto.responseDto.CommentDto;
import com.team03.godchoice.dto.responseDto.PostImgResDto;
import com.team03.godchoice.dto.responseDto.gatherpost.GatherPostResponseDto;
import com.team03.godchoice.exception.CustomException;
import com.team03.godchoice.exception.ErrorCode;
import com.team03.godchoice.interfacepackage.MakeRegionTag;
import com.team03.godchoice.repository.MemberRepository;
import com.team03.godchoice.repository.gatherpost.GatherPostImgRepository;
import com.team03.godchoice.repository.gatherpost.GatherPostLikeRepository;
import com.team03.godchoice.repository.gatherpost.GatherPostRepository;
import com.team03.godchoice.s3.S3Uploader;
import com.team03.godchoice.security.jwt.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GatherPostService implements MakeRegionTag {
    private final MemberRepository memberRepository;
    private final GatherPostRepository gatherPostRepository;
    private final GatherPostImgRepository gatherPostImgRepository;
    private final GatherPostLikeRepository gatherPostLikeRepository;
    private final EventPostService eventPostService;
    private final S3Uploader s3Uploader;

    @Transactional
    public GlobalResDto<?> createGather(GatherPostRequestDto gatherPostDto, List<MultipartFile> multipartFile, UserDetailsImpl userDetails) throws IOException {

        Member member = memberCheck(userDetails);

        // 만남시간, lacalDate로 바꾸고 주소 태그만들고
        log.info("gatherPostDto");
        LocalDate date = LocalDate.parse(gatherPostDto.getDate(), DateTimeFormatter.ISO_DATE);
        RegionTag regionTag = toRegionTag(gatherPostDto.getPostAddress());
        String gatherStatus = eventPostService.toEventStatus(date);

        // dto내용과 사용자 저장
        GatherPost gatherPost = new GatherPost(gatherPostDto, date, regionTag, gatherStatus, member);

        gatherPostRepository.save(gatherPost);

        // List로 image받은후 저장
        saveImg(multipartFile, gatherPost);

        return GlobalResDto.success(new CreateResDto(gatherPost.getGatherPostId()), "success create gatherPost");
    }

    @Transactional
    public GlobalResDto<?> updateGatherPost(Long postId, GatherPostUpdateDto gatherPostDto, List<MultipartFile> multipartFile, UserDetailsImpl userDetails) throws IOException {

        Member member = memberCheck(userDetails);

        GatherPost gatherPost = postCheck(postId);

        if (!gatherPost.getMember().getEmail().equals(member.getEmail())) {
            throw new CustomException(ErrorCode.NO_PERMISSION_CHANGE);
        }

        LocalDate date = LocalDate.parse(gatherPostDto.getDate(), DateTimeFormatter.ISO_DATE);
        String gatherStatus;
        if(gatherPost.getPostStatus().equals("진행중")){
            gatherStatus = gatherPostDto.getPostState();
        }else{
            if(date.isBefore(LocalDate.now())){
                if(gatherPostDto.getPostState().equals("진행중")){
                    throw new CustomException(ErrorCode.DATESTATUS_ERROR);
                }else{
                    gatherStatus = gatherPostDto.getPostState();
                }
            }else{
                gatherStatus = gatherPostDto.getPostState();
            }
        }


        RegionTag regionTag = toRegionTag(gatherPostDto.getPostAddress());
        gatherPost.update(gatherPostDto, date, regionTag, gatherStatus, member);

        String[] imgIdList;

        if (gatherPostDto.getImgId().length()>0) {
            imgIdList = gatherPostDto.getImgId().split(",");

            for (String imgUrl : imgIdList) {
                Long imgId = Long.valueOf(imgUrl);
                GatherPostImg gatherPostImg = gatherPostImgRepository.findById(imgId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_IMG));
                String s3Path = toImgPath(gatherPostImg);
                s3Uploader.delImg(s3Path);
                gatherPostImgRepository.deleteById(imgId);
            }

        }

        saveImg(multipartFile, gatherPost);
        return GlobalResDto.success(null, "수정이 완료되었습니다");
    }

    @Transactional
    public GlobalResDto<?> deleteGatherPost(Long postId, UserDetailsImpl userDetails) {

        Member member = memberCheck(userDetails);

        GatherPost gatherPost = postCheck(postId);

        if (gatherPost.getMember().getEmail().equals(member.getEmail())) {
            List<GatherPostImg> gatherPostImgs = gatherPostImgRepository.findAllByGatherPost(gatherPost);
            if (gatherPostImgs.size() != 0) {
                for (GatherPostImg gatherPostImg : gatherPostImgs) {
                    String imgUrl = toImgPath(gatherPostImg);
                    s3Uploader.delImg(imgUrl);
                }
            }

            gatherPostRepository.deleteById(postId);

            return GlobalResDto.success(null, "success delete gatherpost");
        } else {
            throw new CustomException(ErrorCode.NO_PERMISSION_DELETE);
        }
    }

    public GlobalResDto<?> getGatherPost(Long postId, UserDetailsImpl userDetails) {
        viewCountUp(postId,userDetails.getAccount());

        memberCheck(userDetails);

        GatherPost gatherPost = postCheck(postId);

        List<GatherPostImg> gatherPostImgs = new ArrayList<>(gatherPost.getGatherPostImg());
        List<PostImgResDto> postImgResDtos = new ArrayList<>();
        if (gatherPostImgs.size() == 0) {
            postImgResDtos.add(new PostImgResDto("https://eunibucket.s3.ap-northeast-2.amazonaws.com/testdir/normal_post_img.png", null));
        } else {
            for (GatherPostImg gatherPostImg : gatherPostImgs) {
                postImgResDtos.add(new PostImgResDto(gatherPostImg.getImgUrl(), gatherPostImg.getGatherPostImgId().toString()));
            }
        }

        List<CommentDto> commentDtoList = new ArrayList<>();
        for (GatherPostComment comment : gatherPost.getComments()) {
            if (comment.getParent() == null) {
                commentDtoList.add(0,new CommentDto(comment));
            }
        }

        boolean bookMarkStatus = gatherPostLikeRepository.existsByMemberAndGatherPost(memberCheck(userDetails),gatherPost);

        return GlobalResDto.success(new GatherPostResponseDto(gatherPost, postImgResDtos, commentDtoList,bookMarkStatus), null);

    }

    public Member memberCheck(UserDetailsImpl userDetails) {
        return memberRepository.findById(userDetails.getMember().getMemberId()).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_MEMBER)
        );
    }

    public GatherPost postCheck(Long postId) {
        return gatherPostRepository.findById(postId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_POST)
        );
    }

    public void saveImg(List<MultipartFile> multipartFile, GatherPost gatherPost) throws IOException {
        String gatherPostUrl;
        if (multipartFile != null) {
            for (MultipartFile file : multipartFile) {
                gatherPostUrl = s3Uploader.uploadFiles(file, "testdir");
                GatherPostImg gatherPostImg = new GatherPostImg(gatherPostUrl, gatherPost);
                gatherPostImgRepository.save(gatherPostImg);
            }
        }
    }

    public String toImgPath(GatherPostImg gatherPostImg) {
        List<String> list = List.of(gatherPostImg.getImgUrl().split("/"));
        return list.get(3) + "/" + list.get(4);
    }

    @Transactional
    public void viewCountUp(Long postId, Member member){
        if(member.getPostView()==null || !member.getPostView().contains("[g_"+postId.toString()+"]")){
            member.updatePostView("[g_" + postId+ "],");
            memberRepository.save(member);
            viewCountUp(postId);
        }
    }

    @Transactional
    public void viewCountUp(Long gatherPostId) {
        GatherPost gatherPost = gatherPostRepository.findByGatherPostId(gatherPostId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_POST));
        gatherPost.viewCountUp();
        gatherPostRepository.save(gatherPost);
    }

}
