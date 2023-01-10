

import java.util.ArrayList;
import java.util.List;

public class Work {

	private User studentId;

	private String workName;

    private int numberOfLikes;

    private  List<Comment> comments = new ArrayList<Comment>();

    private String pass;

    public void setStudentId(User studentId){
        this.studentId = studentId;
    }

    public User getStudentId(){
        return this.studentId;
    }

    public void setWorkName(String workName){
        this.workName = workName;
    }

    public String getWorkName(){
        return this.workName;
    }

    public void setNumberOfLikes(int likes){
        this.numberOfLikes = likes;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public void setPass(String pass){
        this.pass = pass;
    }

		public String getPass(){
        return this.pass;
    }

		public List<Comment> getcomments(){
			return this.comments;
		}
}
