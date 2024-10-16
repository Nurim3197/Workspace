package com.mate.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("mate")
	public String viewMainPage(){return "all/main";}

	@GetMapping("header")
	public String viewHeader(){return "header";}

	@GetMapping("footer")
	public String viewFooter(){return "footer";}

	@GetMapping("mate/guide/tourlist")
	public String viewGuideTotalList() {
		return "all/guide_total_tourlist";
	}

	@GetMapping("mate/mypage/guide/calendar")
	public String viewGuideCalendar(){
		return "all/Mypage_Guide_Calendar";
	}
	
	@GetMapping("mate/mypage/guide/editinfo")
	public String viewEditInfo() {
		return "all/Mypage_Guide_EditInfo";
	}
	// sidebar 부분 공통되게 스타일 수정해야함
	
	
	@GetMapping("mate/mypage/guide/mytourlist")
	public String viewGuidsTourList () {
		return "all/Mypage_Guide_MyTour";
	}
	
	@GetMapping("mate/mypage/guide/guide/wishlist")
	public String viewGuideWishList() {
		return "all/Mypage_Guide_Wishlist";
	}
	
	@GetMapping("mate/mypage/tourist/calendar")
	public String viewTouristCalendar() {
		return "all/Mypage_Tourist_Calendar";
	}
	
	@GetMapping("mate/mypage/tourist/editinfo")
	public String viewTouristEditInfo() {
		return "all/Mypage_Tourist_EditInfo";
	}
	// 위와 마찬가지로 사이드바 공통되게
	
	@GetMapping("mate/mypage/tourist/reviewlist")
	public String viewTouristReviewList() {
		return "all/Mypage_Tourist_MyReview";
	}
	
	@GetMapping("mate/mypage/tourist/tourlist")
	public String viewTouristTourList() {
		return "all/Mypage_Tourist_MyTour";
	}
	
	@GetMapping("mate/mypage/tourist/whislist")
	public String viewTouristWishList() {
		return "all/Mypage_Tourist_Wishlist";
	}
	
	@GetMapping("mate/mypage/tourist/payment/Details")
	public String viewTouristPaymentDetails() {
		return "all/MyPageLayout_PaymentDetails";
	}
	
	@GetMapping("mate/mypage/guide/salesManagement")
	public String viewGuideSalesManagement() {
		return "all/MyPageLayout_SalesManagement";
	}
	
	@GetMapping("mate/guide/regist")
	public String viewGuideRegistPage () {
		return "all/reigst_Guide";
	}
	
	
	@GetMapping("mate/guide/tour/write")
	public String viewGuideTourWrite() {
		return "all/Guide_TourInsert";
	}
	
	@GetMapping("mate/guide/recruitment")
	public String viewGuideRecruitment() {
		return "all/GuideRecruitmentPage";
	}
	
	@GetMapping("mate/tourist/tour/write")
	public String viewTouristTourWrite() {
		return "all/Tourist_TourInsert";
	}
	
	
	
}
