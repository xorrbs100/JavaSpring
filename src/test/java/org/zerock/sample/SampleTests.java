package org.zerock.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)		//현재 테스트코드가 스프링을 실행하는 역할 이라는 것을 표시

//중요 : ContextConfiguration어노테이션과 속성값인 문자열 설정
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")		//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록하게 된다.
@Log4j		//Lombok을 이용해서 로그를 기록하는 Logger변수 생성 별도의 선언 없이 Log4j라이브러리와 설정이 존재하면 바로 사용 가능
public class SampleTests {
	
	@Setter(onMethod_ = { @Autowired })		//Autowired는 해당 인스턴스 변수가 스프링으로부터 자동으로 주입해 달라는 표시 정상적으로 주입이 가능하다면 obj변수에 Restaurant타입의 객체 주입
	private Restaurant restaurant;
	
	@Test			//@Test 는 JUnit에서 테스트 대상을 표시하는 어노테이션 JUnit Test  기능 실행
	public void testExist() {		
		assertNotNull(restaurant);		// restaurant 변수가 null 이 아니어야만 테스트가 성공한다는 의미
		
		log.info(restaurant);
		log.info("--------------------");
		log.info(restaurant.getChef());
	}
	
}

/*
 테스트 결과
INFO : org.springframework.test.context.support.DefaultTestContextBootstrapper - Loaded default TestExecutionListener class names from location [META-INF/spring.factories]: [org.springframework.test.context.web.ServletTestExecutionListener, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener, org.springframework.test.context.support.DependencyInjectionTestExecutionListener, org.springframework.test.context.support.DirtiesContextTestExecutionListener, org.springframework.test.context.transaction.TransactionalTestExecutionListener, org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener]
INFO : org.springframework.test.context.support.DefaultTestContextBootstrapper - Using TestExecutionListeners: [org.springframework.test.context.web.ServletTestExecutionListener@3ffc5af1, org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener@5e5792a0, org.springframework.test.context.support.DependencyInjectionTestExecutionListener@26653222, org.springframework.test.context.support.DirtiesContextTestExecutionListener@3532ec19]
INFO : org.springframework.beans.factory.xml.XmlBeanDefinitionReader - Loading XML bean definitions from URL [file:src/main/webapp/WEB-INF/spring/root-context.xml]
INFO : org.springframework.context.support.GenericApplicationContext - Refreshing org.springframework.context.support.GenericApplicationContext@2038ae61: startup date [Mon Mar 01 22:31:16 KST 2021]; root of context hierarchy
INFO : org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor - JSR-330 'javax.inject.Inject' annotation found and supported for autowiring
INFO : org.zerock.sample.SampleTests - Restaurant(chef=Chef())
INFO : org.zerock.sample.SampleTests - --------------------
INFO : org.zerock.sample.SampleTests - Chef()
INFO : org.springframework.context.support.GenericApplicationContext - Closing org.springframework.context.support.GenericApplicationContext@2038ae61: startup date [Mon Mar 01 22:31:16 KST 2021]; root of context hierarchy

  @주목해야할 점
  
  - new Restaurant()과 같이 개게를 생성한 적이 없는데도 객체가 만들어졌다는 점 - 스프링은 관리가 필요한 객체를 어노테이션 등을 이용해서 객체를 생성하고 관리하는 컨테이너,팩토리의 기능을 가짐.
  
  - Restaurant객체의 @Data어노테이션으로 Lombok을 이용해서 여러 메서드가 만들어진 점 - Lombok은 자동으로 getter/setter 등을 만들어 주는데 스프링은 생성자 주입 혹은 setter주입을 이용해서 동작한다. Lombok을 통해 getter/setter등을 자동으로 생성하고
   	'onMethod'속성을 이용해서 작성된 setter에 @Autowired 어노테이션을 추가한다.
   	
  - Restaurant객체의 Chef인스턴스 변수(멤버변수) 에 Chef 타입의 객체가 주입되어 있다는 점 - 스프링은 @Autowired와 같은 어노테이션을 이용해서 개발자가 직접 객체들과의 관계를 관리하지 않고, 자동으로 관리되도록 한다.
 */
 