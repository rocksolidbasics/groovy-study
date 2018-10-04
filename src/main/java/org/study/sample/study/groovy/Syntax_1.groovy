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
		s.stringUsage()
		s.exponentUsage()
		s.listUsage()
		s.arrayUsage()
		s.mapUsage()
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
	
	/**
	 * Quoted identifiers lets us use identifiers which are restricted in Java,
	 * like variables name having space or hyphen in them
	 * @return
	 */
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
	
	def stringUsage() {
		//Single quoted doesn't support interpolation
		def singlquoted = 'abc'
		
		//Concatenation
		def concatStr = 'a' + 'b'
		
		//Triple quoted strings - No interpolation + multiline
		def multi1 = '''Hello
		how are you'''
		println multi1
		//Avoid first newline using backslash
		def multi2 = '''\
		Remove the first newline using backslash character'''
		
		//Double quoted strings of type java.lang.String
		def simpleStr = "simple string"
		//Double quoted string of type groovy.lang.GString
		def string_val = "string"
		def groovyStr = "groovy interpolated ${string_val}"
		
		//Any groovy expression can be used for interpolation. Single line statements
		//may return null, unless the last line in the list of statements is an expression
		def exprStr = "The sum of 2 and 5 is ${def a = 2; def b = 5; a+b}"
		println exprStr
		
		//Use of single $ expression instead of ${} for dotted expressions. The value after the
		//dot can't be a method call, it should be a property of the target object
		def person = [name:'Name', age:1]
		def personStr = "$person.name is $person.age years old"
		
		//Embedded closures
		def c1 = "1 + 2 = ${-> 3}"
		assert c1 == '1 + 2 = 3'
		//w is of type java.io.StringWriter to which the << operator pushes the characters
		def c2 = "1 + 2 = ${ w -> w << 3}"
		assert c2 == '1 + 2 = 3'
		//Closures support lazy evaluation, as an effect the strings behave as mutable strings
		def number = 1
		def eagerStr = "value == ${number}"
		def lazyStr = "value == ${-> number}"
		assert eagerStr == 'value == 1'
		assert lazyStr == 'value == 1'
		number = 2
		assert eagerStr == 'value == 1'
		assert lazyStr == 'value == 2'
		
		//Triple quoted strings - multiline + interpolated
		def interpolation_val = "interpolated value"
		def mStr2 = """Multiline double
		quoted string with ${interpolation_val}"""
		
		//TODO: Slashy string, Dollar slashy string
	}
	
	def characterUsage() { }
	
	def integralLiteralUsage() { }
	
	def decimalLiteralUsage() { }
	
	def exponentUsage() {
		def a = 2 ** 3
		assert a == 8
	}
	
	/**
	 * By default the lists defined in groovy are java.util.List, when not explicitly specified
	 * it is an ArrayList.
	 * Groovy doesn't have its own collections package, instead java packages are used
	 * @return
	 */
	def listUsage() {
		//Default ArrayList
		def numbers = [1, 2, 3]
		assert numbers instanceof List
		assert numbers.size() == 3
		
		//Heterogenous list
		def mixedList = [1, "2", true]
		assert mixedList instanceof java.util.ArrayList
		
		//Type coercion
		def c1List = [1, 2, 3, 4] as LinkedList
		assert c1List instanceof java.util.LinkedList
		
		LinkedList otherList = [3, 4, 5]
		assert otherList instanceof java.util.LinkedList
		
		//List operations
		def lst = ['a', 'b', 'c', 'd']
		assert lst[0] == 'a'
		assert lst[1] == 'b'
		assert lst[-1] == 'd'
		assert lst[-2] == 'c'
		
		lst << 'e'
		assert lst[4] == 'e'
		assert lst[-1] == 'e'
		
		assert lst[1,3] == ['b', 'd']
		assert lst[2..4] == ['c', 'd', 'e']
	}
	
	def arrayUsage() {
		String[] arrStr = ['Apple', 'Banana', 'Guava']
		assert arrStr instanceof String[]
		
		def intArr = [1, 2, 3] as int[]
		assert intArr instanceof int[]
	}
	
	def mapUsage() {
		def colorMap = [red : '#FF0000', green : '#00FF00', blue : '#0000FF']
		assert colorMap['red'] == '#FF0000'
		assert colorMap.green == '#00FF00'
		
		//Using subscript notation
		colorMap['pink'] = '#FF00FF'
		//Using property notation
		colorMap.yellow = '#FFFF00'
		
		assert colorMap.pink == '#FF00FF'
		assert colorMap['yellow'] == '#FFFF00'
		
		assert colorMap instanceof java.util.LinkedHashMap
		
		//Unknown keys will return null
		assert colorMap.unknown == null
		
		//The single quote inside the subscript notation is only in case when the key
		//is a string. For integers it is not needed
		def number = [1 : '1', 2 : '2']
		assert number[1] == '1'
		
		//The value used as a key will be take in as a literal, not as a variable
		def key = 'name'
		def person1 = [key : 'Person1']
		assert !person1.containsKey('name')
		assert person1.containsKey('key')
		//To use a variable's value as key, surround the variable or expression with paranthesis
		def person2 = [(key) : 'Person2']
		assert person2.containsKey('name')
		assert !person2.containsKey('key')
		
	}
}
