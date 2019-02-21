import DTO.UserDTO;
import com.huaban.analysis.jieba.JiebaSegmenter;
import config.DataConfig;
import config.RootConfig;
import dao.AnswerRepository;
import dao.ConversationRepository;
import dao.ProblemRepository;
import dao.UserRepository;
import entity.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AnswerService;
import service.ProblemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, RootConfig.class})
public class TestDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ConversationRepository conversationRepository;
    @Test
    public void testUser(){
        UserEntity userEntity=userRepository.findByUid(1);
        userEntity.setType(UserType.STAFF);
        System.out.println(userRepository.save(userEntity));
        UserEntity userEntity1=new UserEntity();
        userEntity1.setUid(3);
        userRepository.save(userEntity1);
        System.out.println("+++++++++++++++++++++++++++");
        for (UserDTO u:userRepository.findAllUserByKeyword("%%",PageRequest.of(0,2))) {
            System.out.println("+++++++++++++++++++++++++++");
            System.out.println(u);
        }
    }

    @Test
    public void testProblem(){
        ProblemEntity problemEntity=problemRepository.findByPid(1);
        problemEntity.getClassification().setCid(1);
        problemEntity.getAnswer().setAid(2);
        problemRepository.save(problemEntity);
        System.out.println(problemRepository.countAllByContentLikeAndClassification_Cid("%%",1));
    }
    @Test
    public void testjieba(){
        JiebaSegmenter segmenter = new JiebaSegmenter();
        for (ProblemEntity p:problemRepository.findAll()) {
            p.setKeywords(StringUtils.join(segmenter.sentenceProcess(p.getContent())," "));
            problemRepository.save(p);
        }
    }

    @Test
    public void testConversation(){
        System.out.println(conversationRepository.getMeanEvaluate(1));
        ConversationEntity conversationEntity=new ConversationEntity();
        UserEntity userEntity1=new UserEntity();
        userEntity1.setUid(1);
        conversationEntity.setCustomer(userEntity1);
        UserEntity userEntity2=new UserEntity();
        userEntity2.setUid(2);
        conversationEntity.setStaff(userEntity1);
        conversationRepository.save(conversationEntity);
    }

}
