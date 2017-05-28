package fff.util;

import fff.models.Restaurant;

import java.util.Comparator;

public final class Sort {
	public static Comparator<Restaurant> ResNameCompAsc = (o1, o2) -> {
		String resName1 = o1.getName().toUpperCase();
		String resName2 = o2.getName().toUpperCase();
		return resName1.compareTo(resName2);
	};
	public static Comparator<Restaurant> ResNameCompDes = (o1, o2) -> {
		String resName1 = o1.getName().toUpperCase();
		String resName2 = o2.getName().toUpperCase();
		return resName2.compareTo(resName1);
	};
	public static Comparator<Restaurant> ResCuisineCompAsc = (o1, o2) -> {
		String resCui1 = o1.getCuisine().toUpperCase();
		String resCui2 = o2.getCuisine().toUpperCase();
		return resCui1.compareTo(resCui2);
	};
	public static Comparator<Restaurant> ResCuisineCompDes = (o1, o2) -> {
		String resCui1 = o1.getCuisine().toUpperCase();
		String resCui2 = o2.getCuisine().toUpperCase();
		return resCui2.compareTo(resCui1);
	};
	public static Comparator<Restaurant> ResPostcodeCompAsc = (o1, o2) -> {
		int resPost1 = o1.getPostcode();
		int resPost2 = o2.getPostcode();
		return resPost1-resPost2;
	};
	public static Comparator<Restaurant> ResPostcodeCompDes = (o1, o2) -> {
		int resPost1 = o1.getPostcode();
		int resPost2 = o2.getPostcode();
		return resPost2-resPost1;
	};
	public static Comparator<Restaurant> ResRatingCompAsc = (o1, o2) -> {
		int resRating1 = (int) o1.getAverageRatings();
		int resRating2 = (int) o2.getAverageRatings();
		return resRating1-resRating2;
	};
	public static Comparator<Restaurant> ResRatingCompDes = (o1, o2) -> {
		int resRating1 = (int) o1.getAverageRatings();
		int resRating2 = (int) o2.getAverageRatings();
		return resRating2-resRating1;
	};
	public static Comparator<Restaurant> ResPriceCompAsc = (o1, o2) -> {
		int resRating1 = (int) o1.getPriceRange();
		int resRating2 = (int) o2.getPriceRange();
		return resRating1-resRating2;
	};
	public static Comparator<Restaurant> ResPriceCompDes = (o1, o2) -> {
		int resRating1 = (int) o1.getPriceRange();
		int resRating2 = (int) o2.getPriceRange();
		return resRating2-resRating1;
	};
}
