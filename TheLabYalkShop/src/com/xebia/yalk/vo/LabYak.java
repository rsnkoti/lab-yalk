package com.xebia.yalk.vo;

public final class LabYak {

    private String name;
    private double age; 
    private YakSex sex;
    //private boolean skinEligible;
    private boolean milkEligible;

	private LabYak(Builder builder) {
		this.name = builder.name;
		this.age=builder.age;
		this.sex = builder.sex;
		//this.skinEligible =(LabYakService.firstSkinAge * LabYakService.yakYearDays <= this.getAge())? true:false;
		this.milkEligible =(YakSex.FEMALE==this.sex)? true:false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LabYak other = (LabYak) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public double getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public boolean isMilkEligible() {
		return milkEligible;
	}
    
	public static class Builder {
		private String name;
		private double age;
		private YakSex sex;
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder age(double age) {
			this.age = age;
			return this;
		}
		
		public Builder sex(YakSex sex) {
			this.sex = sex;
			return this;
		}
		
		public LabYak build() {
			return new LabYak(this);
		}
		
	}


}
