# Group 21 Repo - WishList

## Get started

We use `maven` where we recommend version 3.8.3 along with Java-16 (SDK-16). 

In order to run this project or in a new environment do the following:

- `mvn clean install`
- `mvn compile`

- `mvn spring-boot:rum`     : To run server (spring boot)

- `mvn -pl fxui javafx:run` : To run JavaFX. (client)

Tests can be done with

- `mvn test`

Verification, checkstyle and spotbugs can be done with

- `mvn verify`

## Workflow

The workflow in this project goes as follows: 
- We usemilestones for each user story and then created subsequent issues linked to that milestone.
- Issues are created as a way to divide a user story into its smallest parts. Issues were then assigned to individual members and reviewers.
- Create Merge requests for Issues for that one other member of the group has to review along side with GitLab CI pipeline tests.
- Usually one developer works on a single issue, but if there are hiccups or one developer is stuck another can come in and pick up where the other left off. It is usually then the latter developer assings him/herself a new task or tries to help along. 


## Naming conventions

We use the following naming conventions:

- FEAT - For developing new feature
- CHANGE - Updating/modifying existing code
- DRAFT - For work in progress code
- DELETE - Removal off code (no additions)
- NO-ISSUE - Misc changes, devops and or updates to dependencies

Commit messages need to follow one of these four tags before additional message.
Commit messages also need to be written in present time.


`FEAT: add new component`

## Code reviews

Everytime a member of the group creates a merge request, it needs to reviewed and verified by atleast one other person before merging it.
After the MR has been verified and accepted, the creator of the MR needs to merge it into the master branch.

Remember to squash commits when merging, since commit messages after doesn't make much sense in the master branch.

## Testing

When implementing a new feature and /or refactoring existing code it is important to update the existing tests and add new additional tests to new features to ensure that the codebase's test coverage either stays the same or increases. 
