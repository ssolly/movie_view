package com.care.root.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.care.root.dto.MovieBookDto;
import com.care.root.dto.SearchMovieDto;
import com.care.root.movieURL.movieURL;

@Service
public class SearchService implements movieURL { // 변수를 쓰기 위해

	RestTemplate movieSearch = new RestTemplate();
	/*
	 * public ArrayList<SearchMovieDto> getMovieView() { String test =
	 * movieSearch.getForObject(movieViewUrl+"/getMovies", String.class);
	 * //movieViewUrl : "10001/search/"까지만 System.out.println("view 결과 확인 : " +
	 * test);
	 * 
	 * ArrayList<SearchMovieDto> list = new ArrayList<SearchMovieDto>(); String
	 * movieURL = "http://localhost:10000/search/movieinfo/"; //제목 클릭 했을 때 이동하기 위한
	 * 경로, 상세보기 for (int i = 1; i < 6; i++) { //임시 서버 SearchMovieDto dto = new
	 * SearchMovieDto(); dto.setMovieId(i + ""); dto.setMovieTitle("화성을 지켜라" + i);
	 * dto.setMovieDate("2200-05-1" + i); Link link = Link.of(movieURL + i); //상세보기
	 * 링크 (=search/movieinfo/i) dto.add(link.withRel(dto.getMovieTitle())); // 경로이름
	 * list.add(dto); } return list; }
	 */

	public SearchMovieDto[] getMovieView() {
		return movieSearch.getForObject(movieViewUrl + "/getMovies", SearchMovieDto[].class); // 배열 형태
	}

	public String booking(MovieBookDto dto) {
		boolean bool = bookingChk(dto.getMovieId(), dto.getBookCount());
		String message;
		if (bool) {	//예매성공
			Calendar cal=Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM//dd-HH시mm분");
			dto.setBookDate(format.format(cal.getTime()));
			int re = movieSearch.postForObject(movieBookUrl+"save/", dto, Integer.class);
			System.out.println("결과(0실패,1성공) : "+re);
			message = "<script>alert('성공')</script>";
		} else {
			message = "<script>alert('실패');location.href='" + movieUrl + "'</script>"; // movieUrl : home
		}
		return message;
	}

	private boolean bookingChk(String movieId, String bookCount) {
		int count /* 잔여티캣 */ = movieSearch.getForObject(movieViewUrl + "getCount/" + movieId, Integer.class);
		if (count < Integer.parseInt(bookCount)) {
			return false;
		} else {	//잔여티켓이 구매티켓보다 많을 때 차감시키는 코드
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("movieId", movieId);
			map.put("count", bookCount);
			movieSearch.put(movieViewUrl + "subCount", map);
			return true;
		}
	}

}
