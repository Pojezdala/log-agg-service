package org.service.entity;

public class AggLogEntity {

	    private String date;
	    private int order;
	    private String country;
	    private String logins;

	    public AggLogEntity() {
	    }
	    
	    public AggLogEntity(String date, String country, String logins) {
			this.date = date;
			this.country = country;
			this.logins = logins;
	    }
	    
	    public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getLogins() {
			return logins;
		}

		public void setLogins(String logins) {
			this.logins = logins;
		}

		@Override
	    public String toString() {
	        return "AggEntity{" +
	                ", date='" + date + '\'' +
	                ", order='" + order + '\'' +
	                ", country='" + country + '\'' +
	                ", logins='" + logins + '\'' +
	                '}';
	    }

	}
