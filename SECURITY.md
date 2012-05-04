# aerogear-security POC

This is a **proof-of-concept** (be kind) on having a very simple security controller built on top of aerogear-controller

### credits / attribution

Has some code/ideas extracted from seam-security, DeltaSpike & TorqueBox.

## References 

* https://github.com/abstractj/aerogear-security/tree/master
* hhttps://github.com/abstractj/aerogear-controller
* https://cwiki.apache.org/confluence/display/DeltaSpike/Security+Module+Drafts (part 3/4)

# Usage

1. Download and install aerogear-security

	git clone https://github.com/abstractj/aerogear-security.git
	git checkout master
	mvn clean install
	
2. Download and install aerogear-controller:
	git clone https://@github.com/abstractj/aerogear-security.git 
	git checkout master
	cd aerogear-controller/aerogear-controller && mvn clean install
	
3. Deploy aerogear-controller demo
	cd aerogear-controller/aerogear-controller-demo && mvn clean package
	cp target/aerogear-controller-demo.war $JBOSS_HOME/standalone/deployments/
	
4. Start JBoss
	$JBOSS_HOME/bin/standalone.sh
	
5. Test it!
	Just open your browser at http://localhost:8080/aerogear-controller-demo/login
	Enter username: John password: Doe
	Yay, you're authenticated!
	
	Now try to close your browser and access: http://localhost:8080/aerogear-controller-demo/login
	
# What's missing?

* Better exception handling
* Unauthorized access Page redirection instead of disgusting HTTP 500
* User/Password cryptography definition
* Session timeout definition
* Prevent weak passwords
* Prevent brute force attack, must add a definite login attempts per unit of time and all the repeated login attempts that were failed should be logged
* Users should be required to change their password periodically