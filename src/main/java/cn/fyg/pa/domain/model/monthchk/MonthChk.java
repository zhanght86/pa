package cn.fyg.pa.domain.model.monthchk;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.shared.state.StateChangeException;

@Entity
public class MonthChk {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	private Long year;
	
	private Long month;

	@Enumerated(EnumType.STRING)
	private MonthChkEnum state;

	@OneToMany(mappedBy = "monthChk",
			fetch = FetchType.EAGER, 
			cascade = {CascadeType.ALL},
			targetEntity = MonthChkItem.class,
			orphanRemoval=true)
	@OrderBy("sn ASC")
	private List<MonthChkItem> monthChkItems=new ArrayList<MonthChkItem>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public Long getMonth() {
		return month;
	}

	public void setMonth(Long month) {
		this.month = month;
	}

	public MonthChkEnum getState() {
		return state;
	}

	public void setState(MonthChkEnum state) {
		this.state = state;
	}

	public List<MonthChkItem> getMonthChkItems() {
		return monthChkItems;
	}

	public void setMonthChkItems(List<MonthChkItem> monthChkItems) {
		this.monthChkItems = monthChkItems;
	}
	
	public void next() throws StateChangeException{
		this.state.next(this);
	}
	
	public void back() throws StateChangeException{
		this.state.back(this);
	}

}
