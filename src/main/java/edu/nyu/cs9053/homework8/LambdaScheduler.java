package homework8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaScheduler {

	private final List<Jobs> jobList;
	private final List<Jobs> resultantList;
	
	public LambdaScheduler(List<Jobs> job){
		jobList=job;
		resultantList=new ArrayList<Jobs>();
	}

	public List<Jobs> getJobList() {
		return jobList;
	}
	 
	public void sortJoblist(){
		 Collections.sort(jobList,new Comparator<Jobs>(){
			 public int compare(Jobs j1,Jobs j2){
				return j1.getEndTime()>=j2.getEndTime()?1:-1;
			 }
		 });
		 
	 }

	public List<Jobs> getResultantList() {
		LambdaScheduler object=new LambdaScheduler(jobList);
		if(jobList.isEmpty()){
		 return null;
		 }
		object.sortJoblist();
		for(int i=0;i<jobList.size();i++){
			System.out.println(jobList.get(i).getStartTime()+","+jobList.get(i).getEndTime());
		}
		
		for(int i=0;i<jobList.size();i++){
			int j=0;
			resultantList.add(jobList.get(i));
			for( j=i+1;j<jobList.size();j++){
				if(jobList.get(i).getEndTime()<=jobList.get(j).getStartTime()){
					resultantList.add(jobList.get(j));
					break;
				}
			}
			i=j+1;
		}
		
		return resultantList;
	}
	 
	
}