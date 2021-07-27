package org.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aggLogData")
public class AggTableEntity {

    @Id
    private int id;

	@Column(name = "date")
    private String date;
    
	@Column(name = "country")
    private String country;

	public AggTableEntity() {
	}
    
    public AggTableEntity(String date, String log_order, String country, String logins) {
        this.date = date;
        this.country = country;
    }
    
	public AggTableEntity id(Integer id) {
		this.id = id;
		return this;
	}
	
	public AggTableEntity date(String date) {
		this.date = date;
		return this;
	}
	
	public AggTableEntity country(String country) {
		this.country = country;
		return this;
	}
	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
    public String toString() {
        return "AggEntity{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
