package org.study.sample.study.groovy

class Syntax_1 {

	String firstName
	String lastName
	int age
	def address
	
	static void main(def args) {
		Syntax_1 s = new Syntax_1()
		s.objectConstruction()
		s.quotedIdentifiers()
	}
	
	def objectConstruction() {
		Syntax_1 oc = new Syntax_1();
		oc.firstName = 'Shyam'
		oc.lastName = 'Sivaraman'
		oc.setAge(1)
		oc.address = ("Street 1")
		println oc;
		
		Syntax_1 oc1 = new Syntax_1(firstName: "Devi", lastName: "Shyam");
		println oc1
	}
	
	def quotedIdentifiers() {
		def map = [:]
		
		map."key1" = "value1"
		map.key2 = "value2"
		map."not-allowed-by-java" = "value3"
		map.'''triple quoted key''' = "value4"
		
		//G-string based keys
		def firstname = "Homer"
		map."Simpson-${firstname}" = "Homer Simpson"
		
		assert map."Simpson-Homer" == "Homer Simpson"
	}
}
