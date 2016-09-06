This is a description of the top level story.

Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: First Scenario
Given a system state
When I do something
Then system is in a different state

Scenario: Second Scenario
Given a system state
When I do something
Then system is in a different state

Scenario: Third Scenario with Examples
Given system is <old_state>
When I perform <action>
Then system should be <new_state>

Examples:
|old_state |action         |new_state   |
|flapping  |pull plug      |falling     |
|proud     |gesture rudely |embarrassed |
|crying    |hug            |happy       |
