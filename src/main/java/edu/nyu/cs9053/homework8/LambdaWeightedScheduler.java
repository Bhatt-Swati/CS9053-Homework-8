package homework8;

import java.util.ArrayList;
import java.util.List;

public class LambdaWeightedScheduler {
	
	private final List<Jobs> resultantList;
	private final List<Jobs> jobList;
	public LambdaWeightedScheduler(List<Jobs> job){
		jobList=job;
		resultantList=new ArrayList<Jobs>();
		
	}

	public void add(Jobs job){
		jobList.add(job);
	}
	
	public List<Jobs> getJobList() {
		return jobList;
	}
	
	public List<Jobs> getResultantList() {
		if(jobList.isEmpty()){
			 return null;
			 }
		LambdaScheduler object=new LambdaScheduler(jobList);
		object.sortJoblist();//sort the list by end time of the jobs
		for(int i=0;i<jobList.size();i++){
			System.out.println(jobList.get(i).getStartTime()+","+jobList.get(i).getEndTime());
		}
		double weightTable[]=new double[jobList.size()];// calculating profits at each job
		//weightTable[0]=jobList.get(0).getWeight();
		for(int i=0;i<jobList.size();i++){
			double weightIncludingCurrent = jobList.get(i).getWeight();
	        int l = compatibleJob(i+1);
	        if (l != -1){
	        	weightIncludingCurrent += weightTable[l];
	        }
	        if(weightIncludingCurrent>weightTable[i-1]){
	        	weightTable[i] = weightIncludingCurrent;
	        	resultantList.add(jobList.get(i));
	        }
	        else{
	        	weightTable[i] = weightTable[i-1];
	        }
	    }
		
		return resultantList;
	}
	
	public int compatibleJob(int i){
		for (int j=i+1;j<=jobList.size();j++){
			if(jobList.get(i).getEndTime()>=jobList.get(j).getStartTime()){
				return j;
			}
		}
		return -1;
	
	}
}