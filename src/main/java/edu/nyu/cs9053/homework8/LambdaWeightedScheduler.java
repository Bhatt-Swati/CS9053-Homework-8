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
		weightTable[0]=jobList.get(0).getWeight();
		double maxWeightTillNow = -1;
		int maxWeightIndex = 0;

		for(int i=1;i<jobList.size();i++){
			double weightIncludingCurrent = jobList.get(i).getWeight();
			int cJ = compatibleJob(i);
			if (cJ != -1){
				weightIncludingCurrent += weightTable[cJ];
			}
			if(weightIncludingCurrent>weightTable[i-1]){
				weightTable[i] = weightIncludingCurrent;
			}
			else{

				weightTable[i] = weightTable[i-1];
			}
			if(weightTable[i]>maxWeightTillNow){
				maxWeightTillNow = weightTable[i];
				maxWeightIndex = i;
			}
		}
		resultantList.add(jobList.get(maxWeightIndex));

		double start=jobList.get(maxWeightIndex).getStartTime(),end=jobList.get(maxWeightIndex).getEndTime();
		for(int i=maxWeightIndex-1;i>=0;--i){

			if(jobList.get(i).getEndTime()<=start){
				resultantList.add(jobList.get(i));
				start = jobList.get(i).getStartTime();
				end=jobList.get(maxWeightIndex).getEndTime();
			}
		}
		return resultantList;
	}

	public int compatibleJob(int i){
		for (int j=i-1;j>=0;j--){
			if(jobList.get(i).getStartTime()>=jobList.get(j).getEndTime()){
				return j;
			}
		}
		return -1;
	}
}