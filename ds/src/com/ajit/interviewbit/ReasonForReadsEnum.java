package com.ajit.interviewbit;

/**
 * Enum Specifying the Reason For Reads.
 * @author Shambhu Mitra
 *
 */
public enum ReasonForReadsEnum {
   //InitialRead , FinalRead, SpecialRead, AfterEvent, BeforeEvent, ScheduledRead, UnscheduledRead, PeriodStart and PeriodEnd.
	
	NULL("N"),
	InitialRead("I"),
	FinalRead("F"),
	SpecialRead("S"),
	AfterEvent("A"),
	BeforeEvent("B"),
	ScheduledRead("C"),
	UnscheduledRead("U"),
	PeriodStart("P"),
	PeriodEnd("E");
	
	
	String name;
	/**
	 * @return name associated with the enum
	 */
	public String getName(){
		return name;
	}
	
	private ReasonForReadsEnum(String name) {
		this.name=name;
	}
	
	/**
	 * @param index
	 * @return Enum For that index
	 */
	public static ReasonForReadsEnum valueOf(final Integer i){
		return i==null? NULL :ReasonForReadsEnum.values()[i];

	}
	
	/**
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return String.valueOf(ordinal());
	}
	
	public static void main(String[] args) {
		String X="FinalRead";
		ReasonForReadsEnum valueOf = ReasonForReadsEnum.valueOf(X);
		System.out.println(valueOf.getName());
	}
}
