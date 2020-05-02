package POJOS;

import java.util.HashSet;

public class CodeforcesProblem {
	long contestId; 
	String index; 
	String name; 
	long rating;
	String url; 
	long totalSolveCount; 
	HashSet<String> tags = new HashSet<String>();

	public CodeforcesProblem(long contestId, String index, String name, long rating, HashSet<String> tags, long totalSolveCount) {
		super();
		this.contestId = contestId;
		this.index = index;
		this.name = name;
		this.rating = rating;
		this.tags = tags;
		this.totalSolveCount = totalSolveCount;
		this.url = "https://codeforces.com/problemset/problem/" + this.contestId + "/" + this.index; 
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getTotalSolveCount() {
		return totalSolveCount;
	}
	public void setTotalSolveCount(int totalSolveCount) {
		this.totalSolveCount = totalSolveCount;
	}
	public long getContestId() {
		return contestId;
	}
	public void setContestId(int contestId) {
		this.contestId = contestId;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public HashSet<String> getTags() {
		return tags;
	}
	public void setTags(HashSet<String> tags) {
		this.tags = tags;
	}
}

/*
	SAMPLE JSON RESPONSE 
	{
  "status": "OK",
  "result": {
    "problems": [
      {
        "contestId": 1343,
        "index": "F",
        "name": "Restore the Permutation by Sorted Segments",
        "type": "PROGRAMMING",
        "rating": 2400,
        "tags": [
          "brute force",
          "constructive algorithms",
          "data structures",
          "greedy",
          "implementation"
        ]
      },
*/
