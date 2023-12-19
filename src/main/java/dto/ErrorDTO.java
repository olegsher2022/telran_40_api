package dto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString

public class ErrorDTO {

//  "timestamp": "2023-12-18T16:33:10.109Z",
//  "status": 0,
//  "error": "string",
//  "message": {},
//  "path": "string"
    String timestamp;
    int status;
    String error;
    String message;
    String path;




}
