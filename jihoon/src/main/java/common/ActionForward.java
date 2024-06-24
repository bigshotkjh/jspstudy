package common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
// 이동할 주소와, 이동 방식을 저장.
public class ActionForward {
  private String path;
  private boolean isRedirect;
}
