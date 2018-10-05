package org.study.sample.study.groovy

import java.util.regex.Pattern

class Operators_1 {

	static void main(def args) {
		Operators_1 ops = new Operators_1();
		ops.unaryOperators();
		ops.conditionOperators();
		ops.objectOperators()
		ops.regexOperator()
	}
	
	def unaryOperators() {
		assert ++1 == 2
		assert 1++ == 1
		
		def f = 3
		f **= 2
		assert f == 9
	}
	
	def conditionOperators() {
		//Not operator
		assert !true == false
		assert !'foo' == false	//foo is a non-empty string, so negation returns false
		assert !''== true		//'' is an empty string
		
		def a = '';
		def b = 'hello'
		def c
		assert !a == true
		assert !b == false
		assert !c == true	//c is null

		//Elvis operator - shortening of default value syntax of ternary operator
		//user.name ? user.name : 'Anonymous' can be written as user.name ?: 'Anonymous'
		def user = [name : null];
		def displayName = user.name ?: 'Anonymous'
		assert displayName == 'Anonymous'
	}
	
	def objectOperators() {
		//Safe navigation operators - To avoid null pointer
		def person = null;
		def name = person?.name;	//person is null, so the safe navigation operator will return null, instead of NPE
		assert name == null
		
		//Direct field access operator, @<property> doesn't call the getter method, instead get the property value directly
		def user = new User("Bob", "M")
		assert user.name == 'Name: Bob'
		assert user.@name == 'Bob'
		assert user.sex == 'M'
		assert user.@sex == 'M'
		
		//Method pointer operator - Stores a reference to a method variable, best used in strategy pattern
		def str = 'Hello how are you'
		def methRef = str.&toUpperCase;
		def ucVal = methRef();
		assert ucVal == str.toUpperCase()
		assert methRef instanceof groovy.lang.Closure
		//Strategy pattern example
		def personList = [new Person(name: 'Bob', age: 1), new Person(name: 'Lilly', age: 2)]
		def desc = this.&personDescribe
		def result = this.personTransform(personList, desc)
		assert result == ['Bob is 1', 'Lilly is 2']
		//Method pointers are bound by receiver and the method name, hence can be called on overloaded method
		//and the resolution of method to call will happen at runtime
		def mRef = this.&doSomething
		assert mRef('Hello') == 'HELLO'
		assert mRef(2) == 4
	}
	
	def regexOperator() {
		def p = ~/foo/
		assert p instanceof Pattern
		def match = p.matcher('foo')
		assert match.find()
	}
	
	def doSomething(String str) { str.toUpperCase() }
	
	def doSomething(int x) { x *= 2 }
	
	def personTransform(List elms, Closure closure) {
		def result = []
		elms.each {
			result << closure(it)
		}
		
		result
	}
	
	def String personDescribe(Person p) {
		"$p.name is $p.age"
	}
	
	class Person {
		String name
		int age
	}
	
	class User {
		public final String name
		private String sex
		User(String name, String s) { this.name = name; this.sex = s }
		String getName() { "Name: $name" }
	}
}
