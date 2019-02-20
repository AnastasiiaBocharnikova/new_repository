$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("bdd/groups.feature");
formatter.feature({
  "line": 1,
  "name": "Groups",
  "description": "",
  "id": "groups",
  "keyword": "Feature"
});
formatter.before({
  "duration": 4216617590,
  "status": "passed"
});
formatter.scenario({
  "line": 3,
  "name": "Group creation",
  "description": "",
  "id": "groups;group-creation",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "a set of groups",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new group with name xxx, header yyy, footer zzz",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of groups is equal to the old set with the added group",
  "keyword": "Then "
});
formatter.match({
  "location": "GroupStepDefinitions.loadGroup()"
});
formatter.result({
  "duration": 272911818,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "xxx",
      "offset": 31
    },
    {
      "val": "yyy",
      "offset": 43
    },
    {
      "val": "zzz",
      "offset": 55
    }
  ],
  "location": "GroupStepDefinitions.createGroup(String,String,String)"
});
formatter.result({
  "duration": 1065725245,
  "status": "passed"
});
formatter.match({
  "location": "GroupStepDefinitions.verifyGroupCreated()"
});
formatter.result({
  "duration": 8483838,
  "status": "passed"
});
formatter.after({
  "duration": 76983349,
  "status": "passed"
});
});