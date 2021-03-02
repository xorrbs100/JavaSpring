package org.zerock.sample;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.Data;
import lombok.Setter;

@Component			// 스프링에게 해당 클래스가 스프링에서 관리해야 하는 대상임을 표시하는 어노테이션
@Data				
public class Restaurant {		//Restaurant 객체는 Chef객체를 필요로 한다.
@Setter (onMethod_=@Autowired)	//@Setter 는 자동적으로 setChef()를 컴파일 시 생성
								//onMethod 속성은 생성되는 setChef()에 @Autowired 어노테이션을 추가하도록 함
private Chef chef;
}
