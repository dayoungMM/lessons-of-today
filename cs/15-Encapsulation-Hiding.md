## 캡슐화 (Encapsulation)

- 중요한 데이터를 보존, 보호하는 것
- 연관있는 변수와 함수를 클래스로 묶음
- 객체의 중요 사항은 감춘 상태에서(은닉화) 외부에서 그것을 사용할 수 있는 방법을 설정하고 그 방법으로만 외부랑 의사소통 (setter, getter)

## 은닉화(Hiding)

- 객체에서 속성을 직접 접근하지 못하게 숨기는 것
- 접근 제어자 private으로 감춘다

## 왜 필요할까?

- private을 사용하는 이유: 다른 사람이 코드를 읽었을 때 해당 메서드나 프로퍼티가 외부에서 호출하는건지 클래스 내부에서만 사용되는 것인지 구분하기 위해
- 객체지향 프로그래밍 관점에서 객체 외부에서 객체 내부로 직접 데이터나 연산에 접근하면 안된다
    - 외부에서 내부 객체에 바로 접근할 수 있으면 의도와 다르게 수정될 수 있으며, 유지보수가 힘들고, 내부에서 데이터가 어디서 어떻게 변경되는지 파악하기 어렵기 때문에 차단. 외부에서 사용가능한 메서드만 public으로 개방

## 예시

```java
public class Hiding {
	private String add = "123456-2345678";
	private int balance = 2000;
	
	void setAdd(String add) {
		this.add = add;
	}
	String getAdd() {
		return add;
	}
	void setBalance(int balance) {
		this.balance = balance;
	}
	int getBalance() {
		return balance;
	}
	public static void main(String[] args) {
		Hiding h = new Hiding();

		h.setAdd("123456-1234123");
		System.out.println(h.getAdd());
		h.setBalance(40000);
		System.out.println(h.getBalance());
	}
}
```