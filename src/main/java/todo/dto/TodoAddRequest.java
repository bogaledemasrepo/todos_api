package todo.dto;

import lombok.Data;

@Data
public class TodoAddRequest {
    private String title;
    private String discription;
}
