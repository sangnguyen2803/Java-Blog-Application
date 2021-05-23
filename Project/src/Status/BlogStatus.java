package Status;


public class BlogStatus{
    protected boolean status;
    
    public BlogStatus(){
        this.status = true;
    }

    BlogStatus(BlogStatus b){
        this.status = b.status;
    }

    public boolean get(){
        boolean ans = this.status;
        return ans;
    }

    public void set(boolean a){
        this.status = a;
    }
}