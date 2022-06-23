package com.tweetapp.api.servicetest;



import static org.junit.jupiter.api.Assertions.assertNotEquals;


import java.util.ArrayList;
import java.util.List;


import com.tweetapp.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;



import com.tweetapp.api.model.Tweet;
import com.tweetapp.api.model.User;
import com.tweetapp.api.repository.TweetRepository;
import com.tweetapp.api.service.TweetServiceImpl;
import com.tweetapp.api.service.UserServiceImpl;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class TweetServiceTest {
    private MockMvc mockMvc;

    @Mock
    private TweetRepository tweetRepo;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TweetServiceImpl tweetServiceMock = new TweetServiceImpl();

    @BeforeEach
    public void setup() {



        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void postTweetTest() {
        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sahaj","Arora","9999898998"));
        when(tweetServiceMock.postTweet(tweet)).thenReturn(tweet);
        Tweet actualresp=tweetServiceMock.postTweet(tweet);
        assertEquals(actualresp,tweet);
    }

    @Test
    public void editTweetTest() {
        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sandhaya","Arora","9999898998"));
        when(tweetServiceMock.editTweet(tweet)).thenReturn(tweet);
        Tweet actualresp=tweetServiceMock.editTweet(tweet);
        assertEquals(actualresp,tweet);
    }

    @Test
    public void likeTweetTest() {
        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setLikes(1);
        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sandhaya","Arora","9999898998"));
        when(tweetServiceMock.likeTweet(tweet)).thenReturn(tweet);
        Tweet actualresp=tweetServiceMock.likeTweet(tweet);
        assertEquals(actualresp,tweet);
    }

    @Test
    public void replyTweetTest() {
        Tweet tweet3 = new Tweet();
        tweet3.setId("1");
        tweet3.setTweetName("abcdef");
        tweet3.setUser(new User("1","sanchit","root","root","abc@gmail.com","sahaj","Arora","9999898998"));
        List<Tweet> list=new ArrayList<Tweet>();
        list.add(tweet3);


        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sahaj","Arora","9999898998"));
        tweet.setReplies(list);
        Tweet tweet1 = new Tweet();
        tweet1.setId("2");
        tweet1.setTweetName("wxyzabc");
        tweet1.setUser(new User("2","aaple","root","root","xyz@gmail.com","Treena","Mira","7766655432"));

        Tweet actualresp=tweetServiceMock.replyTweet(tweet,tweet1);
        when(tweetServiceMock.replyTweet(tweet,tweet1)).thenReturn(tweet1);
        assertEquals(actualresp,tweet1);
    }


//    @Test
//    public void deleteTweetTest() {
//        ArgumentCaptor<Tweet> arg=ArgumentCaptor.forClass(Tweet.class);
//        Tweet tweet = new Tweet();
//        tweet.setId("1");
//        tweet.setTweetName("abcdef");
//        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sandhaya","Arora","9999898998"));
//        tweetServiceMock.deleteTweet(tweet);
//       // verify(tweetRepo).delete(arg.capture().getId());
//        assertEquals("1",arg.getValue().getId());
//    }





    @Test
    public void getAlltweetsTest() {
        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sahaj","Arora","9999898998"));
        Tweet tweety = new Tweet();
        tweety.setId("1");
        tweety.setTweetName("abcdef");
        tweety.setUser(new User("2","sahil","root","root","abd@gmail.com","sahil","Arora","9999898997"));
        List<Tweet> tweetList = new ArrayList<Tweet>();
        tweetList.add(tweet);
        tweetList.add(tweety);
        when(tweetServiceMock.getAllTweets()).thenReturn(tweetList);
        List<Tweet> tweetListResp=tweetServiceMock.getAllTweets();
        assertEquals(tweetListResp, tweetList);


    }
    @Test
    public void getallTweetsByUserNameTest() {
        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setUser(new User("1","sanchi","root","root","abc@gmail.com","sahaj","Arora","9999898998"));
        Tweet tweety = new Tweet();
        tweety.setId("1");
        tweety.setTweetName("abcdef");
        tweety.setUser(new User("2","sahil","root","root","abd@gmail.com","sahil","Arora","9999898997"));
        List<Tweet> tweetList = new ArrayList<Tweet>();
        tweetList.add(tweet);
        tweetList.add(tweety);
        when(tweetServiceMock.getAllTweetsByUsername("sanchit")).thenReturn(tweetList);
        List<Tweet> tweetListResp=tweetServiceMock.getAllTweetsByUsername("sanchit");
        assertEquals(tweetListResp, tweetList);


    }



    @Test
    public void postTweetsByUserNameTest() {
        User user=new User();
        user.setId("1");
        user.setUsername("sanchi");
        user.setFirstName("sahaj");
        user.setLastName("Arora");

        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("sanchi");
        when(userRepository.findByUsername("sanchit")).thenReturn(user);
        tweet.setUser(new User("1","sanchi","root","root","abc@gmail.com","sahaj","Arora","9999898998"));
        Tweet tweetListResp=tweetServiceMock.postTweetByUsername(tweet,"sanchi");
        assertNotEquals(tweetListResp, tweet);
    }

    @Test
    public void likeTweetByIdTest() {
        Tweet tweet = new Tweet();
        tweet.setId("1");
        tweet.setTweetName("abcdef");
        tweet.setLikes(1);
        tweet.setUser(new User("1","sanchit","root","root","abc@gmail.com","sandhaya","Arora","9999898998"));
        tweetServiceMock.postTweet(tweet);
        tweetServiceMock.likeTweetById(tweet.getId());
       // when(tweet.setLikes(1)).thenReturn(tweet)
        TweetServiceImpl myList = mock(TweetServiceImpl.class);
        doNothing().when(myList).likeTweetById( isA(String.class));
        myList.likeTweetById("1");

        verify(myList, times(1)).likeTweetById( "1");

    }
}