package di.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="goods")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Good {
	@Id
	private int code;
	@Column
	private String name;
	@Column
	private String manufacture;
	@Column
	private int price;
	@Column
	private Date shelflife;

}
