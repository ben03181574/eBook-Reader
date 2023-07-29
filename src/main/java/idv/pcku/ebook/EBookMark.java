package idv.pcku.ebook;

import java.util.TreeSet;

/**
 * 
 * @author KuoPoCheng
 *
 */
public class EBookMark {
	private TreeSet<Integer> eBookMarks;
	
	/**
	 * 
	 */
	public EBookMark() {
		eBookMarks = new TreeSet<>();
	}
	
	/**
	 * 
	 */
	public void resetEBookMark() {
		this.eBookMarks.clear();
	}
	
	/**
	 * 
	 * @param pageNum
	 */
	public void appendEBookMark(int pageNum) {
		this.eBookMarks.add(pageNum);
	}
	
	/**
	 * 
	 * @param pageNum
	 */
	public void deleteEBookMark(int pageNum) {
		this.eBookMarks.remove(pageNum);
	}
	
	/**
	 * 
	 * @return
	 */
	public TreeSet<Integer> getEBookMark(){
		return this.eBookMarks;
	}
}
