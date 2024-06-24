package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* DTO : Data Transfer Object */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookDTO { //책 개별정보 저장.
  private int book_no;
  private String title;
  private String author;
  private int price;
}
