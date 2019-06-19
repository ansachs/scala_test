# Getting Dressed App

##instructions
1. git clone current repository
2. install JDK 8 and SBT [sbt install page](https://www.scala-sbt.org/release/docs/Setup.html)
3. in cloned directory type `sbt clean compile`
4. to run from command line type `sbt "run [args]"` where args is a either `HOT` or `COLD` followed by the numbers `1-8` <br>
 example: `sbt "run COLD 8 4 5"`
5. if run in the sbt shell, the arguments should be comma separated \
example: `sbt:getting-dressed> run COLD 1, 2, 3`

##notes
Tried to achieve the following in this program:

1. utilize a single source of truth for the applied actions. \
Thus a match condition that applies that action and rules which \
test the person's state after application of the action

2. apply rules to actions in a functional manner, thus wrapping results in a try block
3. separate program flow from rules -> rules have no concept of actions => only test validity of a state
4. separate Person class from rules -> ie person has no concept of rules => thus use of Option[Int] in defining clothing items

##future releases
- requires more testing -> only implemented a few basic tests to check on libraries
- requires handling edge cases -> I have not written the program to handle bad arguments \
or args that are improperly formatted 
