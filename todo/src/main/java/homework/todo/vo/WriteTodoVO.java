package homework.todo.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class WriteTodoVO {
	
	@NotBlank
	@Size(max=100)
	private String content;
	
	@NotBlank
	private String deadLine;
	
	private String email;
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(String deadLine) {
		if (deadLine.matches(".*[가-힣]+.*")) {
			deadLine = null;
		}
		this.deadLine = deadLine;
	}
}
