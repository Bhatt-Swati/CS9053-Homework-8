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
		double weightTable[]=new double[jobList.size()];// calculating profits at each job
		for(int i=0;i<=jobList.size();i++){
			double weightIncludingCurrent = jobList.get(i).getWeight();
	        int l = compatibleJobs(i);
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
	
	public int compatibleJobs(int i){
		for (int j=i-1;j>=0;j--){
			if(jobList.get(j).getEndTime()<=jobList.get(i-1).getStartTime()){
				return j;
			}
		}
		return -1;
	
	}
}