import dao.GreetingRepository;
import dto.UserDTO;
import com.huaban.analysis.jieba.JiebaSegmenter;
import config.DataConfig;
import config.RootConfig;
import dao.ConversationRepository;
import dao.ProblemRepository;
import dao.UserRepository;
import entity.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.AnswerService;
import service.ConversationService;
import service.ProblemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataConfig.class, RootConfig.class})
public class TestDao {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GreetingRepository greetingRepository;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private ConversationService conversationService;
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
    public void testGreeting(){
        System.out.println(greetingRepository.findAll());
        System.out.println(greetingRepository.findAllByContentLike("%您好%",PageRequest.of(1,20)));
    }

    @Test
    public void testConversation(){
        conversationRepository.findConversationByKeyword(PageRequest.of(1,20),"ya");
        conversationService.endConversation(1,0.0);
        System.out.println(conversationRepository.findByCid(1));
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
