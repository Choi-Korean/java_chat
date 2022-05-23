package service;

import java.util.ArrayList;


public class Model {

		private static Model instance = new Model();

		private ArrayList<TalentDonationProject> donationProjectList = new ArrayList<TalentDonationProject>();
		

		/** 진행중인 Project 총 개수 : 배열에 저장시마다 index 값 적용, 저장 후에는 index값을 1씩 증가하는 update 실행 */
		
		// 생성자 private으로 하여 외부에서 접근 못하게
		private TalentDonationProjectModel() {}
		
		// 외부에서 instance(위에서 만든 생성자) 받을 수 있게 public 메서드 생성. 외부에서 객체 생성 못하기에 static으로
		// 만들어줘야 함
		public static TalentDonationProjectModel getInstance() {
			return instance;
		}
		

		/**
		 * 모든 Project 검색
		 * 
		 * @return 모든 Project
		 */
		// 위에서 만든 project 배열 전체 return
		public ArrayList<TalentDonationProject> getDonationProjectsList() {
			return donationProjectList;
		}

		

		/** 
		 * @param projectName 프로젝트 이름
		 * @return TalentDonationProject 검색된 프로젝트
		 * @exception 검색하는 project가 미존재할 경우 NotFoundProjectException 발생 
		 */
		
		// parameter로 들어온 projectName에 해당하는 project만 return
		// project없을때 exception 처리할 것임
		public TalentDonationProject getDonationProject(String projectName) throws NotFoundProjectException{
			
			// donationProjectList에 담긴 project 하나씩 꺼내서 tp에 저장
			for(TalentDonationProject tp : donationProjectList) {
				if(tp.getTalentDonationProjectName().equals(projectName)) {
					return tp;
				}
			}
			throw new NotFoundProjectException("고객님이 검색하신 " + projectName + "는 존재하지 않습니다.");
		}
		

		// TO DO
		/**
		 * 새로운 Project 추가 
		 * 
		 * @param project 저장하고자 하는 새로운 프로젝트
		 */
		public void donationProjectInsert(TalentDonationProject project) {
			donationProjectList.add(project);	// 데이터 자동으로 index 적용하면서 저장
		}

		
		/**
		 * Project의 기부자 수정 - 프로젝트 명으로 검색해서 해당 프로젝트의 기부자 수정
		 * 
		 * @param projectName 프로젝트 이름
		 * @param people 기부자 
		 */
		// parameter로 projectName와 people 받아서, 일치하는 projectName찾은 후, 해당 project의
		// people 수정. 수정만 하고 return 안해도 되니까 void
		public void donationProjectUpdate(String projectName, Donator people) throws NotFoundProjectException{
			
			// foreach문으로 donationProjectList에서 하나씩 꺼내서 project에 담기
			// project가 null이 아니고, projectName이 일치하면 setDonator로 기부자명 people로 수정
			// if로 해당로직 돌았으면 break하여 for문 빠져나오기
			int index = 1;
			for(TalentDonationProject project : donationProjectList) {
				if(project.getTalentDonationProjectName().equals(projectName)) {
					project.setProjectDonator(people);
					break;
				}else {
					index ++;
					if(index > donationProjectList.size()) {
						throw new NotFoundProjectException("요청하신 Project : " + projectName + "는 존재하지 않습니다.");
					}
				}
			}
		}
		
		//TO DO
		/**
		 * Project의 수혜자 수정 - 프로젝트 명으로 검색해서 해당 프로젝트의 수혜자 수정
		 * 
		 * @param projectName 프로젝트 이름
		 * @param people 수혜자 
		 */
		// 기부자 수정과 동일
		public void beneficiaryProjectUpdate(String projectName, Beneficiary people) throws NotFoundProjectException{
			for(TalentDonationProject project : donationProjectList) {
				if(project.getTalentDonationProjectName().equals(projectName)) {
					project.setProjectBeneficiary(people);
					return;
				}
			}
			throw new NotFoundProjectException(projectName + "가 존재하지 않습니다.");
		}
		
		
		//TO DO
		/**
		 * Project 삭제 - 프로젝트 명으로 해당 프로젝트 삭제
		 * 
		 * @param projectName 삭제하고자 하는 프로젝트 이름
		 */
		// parameter로 받은 projectName와 일치하는 값 찾아서 삭제할 것
		
		/*	논리적 문제 발생
		 * index 관리
		 * 배열길이 반환을 위해 index--; 로직 추가하면
		 * 	insert시에 배열[index] = 새로운 project;로 하는데, 그럼 문제되지.
		 *  pop 시켜야지 뭐. 또는 재정렬하나봐. 똑같은거 같긴 한데
		 *  배열이 아닌 list 써야 된대. 그래서 arraylist의 remove로 대체
		 */
		public void donationProjectDelete(String projectName) throws NotFoundProjectException{
//			TalentDonationProject project = null;
//			for(int i=0; i< donationProjectList.size() ; i++) {
//				project = donationProjectList.get(i);
//				
//				if(project != null && project.getTalentDonationProjectName().equals(projectName)) {
//					donationProjectList.remove(i);
//					break;
//				}
//			}
			// remove(Object)로 지우기. 위는 index로
			for(TalentDonationProject project : donationProjectList) {
				if(project.getTalentDonationProjectName().equals(projectName)) {
				donationProjectList.remove(project);
//				break;	// 반복문만 종료
				return; // 값이 없는 상태의 return; 문장은 void를 반환하는 메소드 종료를 의미
				}
			}
			
			throw new NotFoundProjectException("삭제하려는 " + projectName + "은 존재하지 않습니다.");
		}
		
		
		/**
		 * 진행중인 Project 총 개수 반환
		 * @return 개수
		 */
		// project 생성할때마다 늘렸던 index 반환해서 총 project 개수 반환
		public int projectListSize() {
			return donationProjectList.size();
		}
}
