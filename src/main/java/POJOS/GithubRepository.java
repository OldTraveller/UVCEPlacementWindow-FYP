package POJOS;

public class GithubRepository {
	String repositoryFullName; // full_name
	String repositoryHTMLUrl; // html_url
	String repositoryDescription; // description
	String repositoryFurtherInformation; // url
    String repositoryCreatedAt; //"created_at": "2019-10-18T11:09:24Z",
    String repositoryUpdatedAt; //"updated_at": "2019-10-18T11:09:25Z",
    String repositoryPushedAt;//"pushed_at": "2019-10-19T13:08:22Z",
    
    
	public String getRepositoryFullName() {
		return repositoryFullName;
	}
	public void setRepositoryFullName(String repositoryFullName) {
		this.repositoryFullName = repositoryFullName;
	}
	public String getRepositoryHTMLUrl() {
		return repositoryHTMLUrl;
	}
	public void setRepositoryHTMLUrl(String repositoryHTMLUrl) {
		this.repositoryHTMLUrl = repositoryHTMLUrl;
	}
	public String getRepositoryDescription() {
		return repositoryDescription;
	}
	public void setRepositoryDescription(String repositoryDescription) {
		this.repositoryDescription = repositoryDescription;
	}
	public String getRepositoryFurtherInformation() {
		return repositoryFurtherInformation;
	}
	public void setRepositoryFurtherInformation(String repositoryFurtherInformation) {
		this.repositoryFurtherInformation = repositoryFurtherInformation;
	}
	public String getRepositoryCreatedAt() {
		return repositoryCreatedAt;
	}
	public void setRepositoryCreatedAt(String repositoryCreatedAt) {
		this.repositoryCreatedAt = repositoryCreatedAt;
	}
	public String getRepositoryUpdatedAt() {
		return repositoryUpdatedAt;
	}
	public void setRepositoryUpdatedAt(String repositoryUpdatedAt) {
		this.repositoryUpdatedAt = repositoryUpdatedAt;
	}
	public String getRepositoryPushedAt() {
		return repositoryPushedAt;
	}
	public void setRepositoryPushedAt(String repositoryPushedAt) {
		this.repositoryPushedAt = repositoryPushedAt;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("============================================================\n");
		builder.append("REPO FULL NAME : " + repositoryFullName + "\n");
		builder.append("REPO HTML URL  : " + repositoryHTMLUrl + "\n");
		builder.append("REPO DESC  : " + repositoryDescription + "\n");
		builder.append("REPO FURTHER   : " + repositoryFurtherInformation + "\n");
		builder.append("============================================================\n");
		
		return builder.toString();
	}
}

/*
		SAMPLE JSON RESPOSE FOR THE REPOSITORY API of Github!
[
  {
    "id": 216005956,
    "node_id": "MDEwOlJlcG9zaXRvcnkyMTYwMDU5NTY=",
    "name": "Algorithms",
    "full_name": "OldTraveller/Algorithms",
    "private": false,
    "owner": {
      "login": "OldTraveller",
      "id": 42368901,
      "node_id": "MDQ6VXNlcjQyMzY4OTAx",
      "avatar_url": "https://avatars1.githubusercontent.com/u/42368901?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/OldTraveller",
      "html_url": "https://github.com/OldTraveller",
      "followers_url": "https://api.github.com/users/OldTraveller/followers",
      "following_url": "https://api.github.com/users/OldTraveller/following{/other_user}",
      "gists_url": "https://api.github.com/users/OldTraveller/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/OldTraveller/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/OldTraveller/subscriptions",
      "organizations_url": "https://api.github.com/users/OldTraveller/orgs",
      "repos_url": "https://api.github.com/users/OldTraveller/repos",
      "events_url": "https://api.github.com/users/OldTraveller/events{/privacy}",
      "received_events_url": "https://api.github.com/users/OldTraveller/received_events",
      "type": "User",
      "site_admin": false
    },
    "html_url": "https://github.com/OldTraveller/Algorithms",
    "description": "Collection of algorithms in multiple programming languages.",
    "fork": true,
    "url": "https://api.github.com/repos/OldTraveller/Algorithms",
    "forks_url": "https://api.github.com/repos/OldTraveller/Algorithms/forks",
    "keys_url": "https://api.github.com/repos/OldTraveller/Algorithms/keys{/key_id}",
    "collaborators_url": "https://api.github.com/repos/OldTraveller/Algorithms/collaborators{/collaborator}",
    "teams_url": "https://api.github.com/repos/OldTraveller/Algorithms/teams",
    "hooks_url": "https://api.github.com/repos/OldTraveller/Algorithms/hooks",
    "issue_events_url": "https://api.github.com/repos/OldTraveller/Algorithms/issues/events{/number}",
    "events_url": "https://api.github.com/repos/OldTraveller/Algorithms/events",
    "assignees_url": "https://api.github.com/repos/OldTraveller/Algorithms/assignees{/user}",
    "branches_url": "https://api.github.com/repos/OldTraveller/Algorithms/branches{/branch}",
    "tags_url": "https://api.github.com/repos/OldTraveller/Algorithms/tags",
    "blobs_url": "https://api.github.com/repos/OldTraveller/Algorithms/git/blobs{/sha}",
    "git_tags_url": "https://api.github.com/repos/OldTraveller/Algorithms/git/tags{/sha}",
    "git_refs_url": "https://api.github.com/repos/OldTraveller/Algorithms/git/refs{/sha}",
    "trees_url": "https://api.github.com/repos/OldTraveller/Algorithms/git/trees{/sha}",
    "statuses_url": "https://api.github.com/repos/OldTraveller/Algorithms/statuses/{sha}",
    "languages_url": "https://api.github.com/repos/OldTraveller/Algorithms/languages",
    "stargazers_url": "https://api.github.com/repos/OldTraveller/Algorithms/stargazers",
    "contributors_url": "https://api.github.com/repos/OldTraveller/Algorithms/contributors",
    "subscribers_url": "https://api.github.com/repos/OldTraveller/Algorithms/subscribers",
    "subscription_url": "https://api.github.com/repos/OldTraveller/Algorithms/subscription",
    "commits_url": "https://api.github.com/repos/OldTraveller/Algorithms/commits{/sha}",
    "git_commits_url": "https://api.github.com/repos/OldTraveller/Algorithms/git/commits{/sha}",
    "comments_url": "https://api.github.com/repos/OldTraveller/Algorithms/comments{/number}",
    "issue_comment_url": "https://api.github.com/repos/OldTraveller/Algorithms/issues/comments{/number}",
    "contents_url": "https://api.github.com/repos/OldTraveller/Algorithms/contents/{+path}",
    "compare_url": "https://api.github.com/repos/OldTraveller/Algorithms/compare/{base}...{head}",
    "merges_url": "https://api.github.com/repos/OldTraveller/Algorithms/merges",
    "archive_url": "https://api.github.com/repos/OldTraveller/Algorithms/{archive_format}{/ref}",
    "downloads_url": "https://api.github.com/repos/OldTraveller/Algorithms/downloads",
    "issues_url": "https://api.github.com/repos/OldTraveller/Algorithms/issues{/number}",
    "pulls_url": "https://api.github.com/repos/OldTraveller/Algorithms/pulls{/number}",
    "milestones_url": "https://api.github.com/repos/OldTraveller/Algorithms/milestones{/number}",
    "notifications_url": "https://api.github.com/repos/OldTraveller/Algorithms/notifications{?since,all,participating}",
    "labels_url": "https://api.github.com/repos/OldTraveller/Algorithms/labels{/name}",
    "releases_url": "https://api.github.com/repos/OldTraveller/Algorithms/releases{/id}",
    "deployments_url": "https://api.github.com/repos/OldTraveller/Algorithms/deployments",
    "created_at": "2019-10-18T11:09:24Z",
    "updated_at": "2019-10-18T11:09:25Z",
    "pushed_at": "2019-10-19T13:08:22Z",
    "git_url": "git://github.com/OldTraveller/Algorithms.git",
    "ssh_url": "git@github.com:OldTraveller/Algorithms.git",
    "clone_url": "https://github.com/OldTraveller/Algorithms.git",
    "svn_url": "https://github.com/OldTraveller/Algorithms",
    "homepage": "",
    "size": 1186,
    "stargazers_count": 0,
    "watchers_count": 0,
    "language": null,
    "has_issues": false,
    "has_projects": true,
    "has_downloads": true,
    "has_wiki": true,
    "has_pages": false,
    "forks_count": 0,
    "mirror_url": null,
    "archived": false,
    "disabled": false,
    "open_issues_count": 0,
    "license": {
      "key": "apache-2.0",
      "name": "Apache License 2.0",
      "spdx_id": "Apache-2.0",
      "url": "https://api.github.com/licenses/apache-2.0",
      "node_id": "MDc6TGljZW5zZTI="
    },
    "forks": 0,
    "open_issues": 0,
    "watchers": 0,
    "default_branch": "master"
  }
]
*/