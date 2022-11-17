package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends StudyApplicationTests {

    //Dependency Injection (DI)를 Autowired 키워드를 통해 사용
    @Autowired
    private UserRepository userRepository ;
    //보통 private UserRepository userRepository = new UserRepository();
    //이렇게 객체를 불러와서 사용하는데 Autowired를 통해 스프링의 가장 큰 장점인 DI(의존성 주입)
    //의존성 주입을 통해 스프링이 직접 관리해준다
    //DI는 기본적으로 싱글톤이다

    @Test
    public void create(){
        // 다른 프레임 워크에서는 데이터베이스에 insert를 할때
        // String sql = insert into user (%s,%s,%d) value (account, email, age);
        // 이런식으로 쿼리문을 통해 값들을 매칭시키는데
        // 하지만 JPA는 오브젝트를 가지고 DB를 관리하도록 도와주는 툴이므로 편리하게 해줌

        User user= new User();
        // user.setId();는 자동 증가로 안넣어도 됨 AI
        user.setAccount("TestUser02");
        user.setEmail("TestUser02@gmail.com");
        user.setPhoneNumber("010-0000-1111");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("user2");

        // user타입을 넣었기 때문에 return도 user타입으로 되므로 user로 받는다.
        User newUser = userRepository.save(user);

        System.out.println("newUser : " + newUser);

    }

    //똑같이 userRepository를 통해 들고올것임
    //기본적으로 JPA의 repository를 상속받으면 CRUD를 제공해준다
    @Test
    public void read(){
        //userRepository.findAll(); 다 받아오기
        //findById가 Optional형태로 리턴을 시켜줌
        Optional<User> user = userRepository.findById(2L);

        //Optional은 말그대로 있을수도있고 없을수도 있기때문에
        //ifPresent를 통해 존재할때만 출력
        user.ifPresent(selectUser ->{
            System.out.println("user : " + selectUser);
            System.out.println("email : " + selectUser.getEmail());
        });

    }
    //예를들어 Rest api에서 read(@RequestParam = Long id)로 id가 들어오면
    //그 id를 select해서 해당 id의 User를 출력가능


    //create()할때도 값세팅후 save를 사용해서 했는데 update()와 어떻게 구분하는가?
    // 값을 바꿔서 save를 하게되면 JPA에서는 해당 아이디가 존재하는지 확인하고
    // 만약 존재한다면 그 row에 대해서 update를 쳐준다
    // 즉, findById를 통해 특정 id를 select 했을 거고
    // JPA가 selectUser의 값을 세팅한다음 selectUser의 ID값을 통해 다시 select를 하고 기존에 있는 값에다가 update 쳐줌

    @Test
    public void update(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }


    //update와 마찬가지로 먼저 select부터 한다
    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(2L);

        user.ifPresent(selectUser->{
            userRepository.delete(selectUser);
            //delete는 로우를 지운것이기 때문에 void를 반환
        });

        // 데이터 삭제 되었는지 확인하기
        Optional<User> deleteUser = userRepository.findById(2L);

        if(deleteUser.isPresent()){
            System.out.println("데이터 존재 : " + deleteUser.get());
        }else{
            System.out.println("데이터 삭제됨");
        }


    }
    // 예를 들어 @DeleteMapping("/api/user")
    // public void delete(@RequestParam Long id) 처럼 활용 가능


}
