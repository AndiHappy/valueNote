package redissource;

import java.util.Arrays;

/**
 * redis 的字符串 1.常数复杂度获取字符串长度 2.杜绝缓冲区溢出
 * 3.在SDS中，buf数组的长度不一定就是字符数量加一，数组里面可以包含未使用的字节，而这些字节的数量就由SDS的free属性记录。
 * 4.减少修改字符串时带来的内存重分配次数,通过未使用空间，SDS实现了空间预分配和惰性空间释放两种优化策略。
 * 5.当SDS的API对一个SDS进行修改，并且需要对SDS进行空间扩展的时候，程序不仅会为SDS分配修改所必须要的空间，还会为SDS分配额外的未使用空间。
 * 6.惰性空间释放用于优化SDS的字符串缩短操作：当SDS的API需要缩短SDS保存的字符串时，
 * 程序并不立即使用内存重分配来回收缩短后多出来的字节，而是使用free属性将这些字节的数量记录起来，并等待将来使用。 7. 可以保存二进制或者文本数据
 */
public class SDS {

	public static final int SDS_MAX_PREALLOC = 1024 * 1024;

	// 记录buf数组中已使用字节的数量 // 等于SDS所保存字符串的长度
	public int len;
	// 记录buf数组中未使用字节的数量
	public int free;
	// 字节数组，用于保存字符串
	public char buf[];

	// ------新建-----------------
	public static SDS sdsnewlen(char[] ini, int length) {
		SDS sds = new SDS();
		sds.len = length;
		sds.free = 0;
		sds.buf = new char[length];

		if (ini != null && ini.length > 0 && length > 0) {
			int srcPos = 0;
			int destPos = 0;
			sds.buf = new char[length];
			System.arraycopy(ini, srcPos, sds.buf, destPos, length);
		}
		return sds;
	}

	public static SDS sdsnew(char[] ini) {
		int length = ini != null && ini.length > 0 ? ini.length : 0;
		return sdsnewlen(ini, length);
	}

	public static SDS sdsempty() {
		char[] ini = "".toCharArray();
		int length = ini != null && ini.length > 0 ? ini.length : 0;
		return sdsnewlen(ini, length);
	}

	// ---------------拼接--------------------------
	public static SDS sdscat(SDS s, char[] t) {
		return sdscatlen(s, t, t.length);
	}

	public static SDS sdscatsds(SDS s, SDS t) {
		return sdscatlen(s, t.buf, t.buf.length);
	}

	public static SDS sdscatlen(SDS s, char[] buf, int length) {
		int currlen = sdslen(s);
		s = sdsMakeRoomFor(s, length);
		System.arraycopy(buf, 0, s.buf, currlen, length);
		s.len = currlen + length;
		s.free -= length;
		return s;
	}

	private static SDS sdsMakeRoomFor(SDS s, int addlen) {
		int free = sdsavail(s);
		int len, newlen;
		if (free >= addlen)
			return s;
		len = sdslen(s);
		newlen = (len + addlen);
		if (newlen < SDS_MAX_PREALLOC) {
			newlen *= 2;
		} else {
			newlen += SDS_MAX_PREALLOC;
		}

		char[] newchar = new char[newlen];
		System.arraycopy(s.buf, 0, newchar, 0, len);
		s.buf = null;
		s.buf = newchar;
		s.free = newlen - len;
		return s;
	}

	private static int sdsavail(SDS s) {
		return s == null ? 0 : s.free;
	}

	public static int sdslen(SDS s) {
		return s == null ? 0 : s.len;
	}

	/**
	 * Redis中SDS支持的方法 
	
	sds sdsnewlen(const void *init, size_t initlen);
	sds sdsnew(const char *init);
	sds sdsempty(void);
	size_t sdslen(const sds s);
	sds sdsdup(const sds s);
	void sdsfree(sds s);
	size_t sdsavail(const sds s);
	sds sdsgrowzero(sds s, size_t len);
	sds sdscatlen(sds s, const void *t, size_t len);
	sds sdscat(sds s, const char *t);
	sds sdscatsds(sds s, const sds t);
	sds sdscpylen(sds s, const char *t, size_t len);
	sds sdscpy(sds s, const char *t);
	
	sds sdscatvprintf(sds s, const char *fmt, va_list ap);
	#ifdef __GNUC__
	sds sdscatprintf(sds s, const char *fmt, ...)
	__attribute__((format(printf, 2, 3)));
	#else
	sds sdscatprintf(sds s, const char *fmt, ...);
	#endif
	
	sds sdscatfmt(sds s, char const *fmt, ...);
	sds sdstrim(sds s, const char *cset);
	void sdsrange(sds s, int start, int end);
	void sdsupdatelen(sds s);
	void sdsclear(sds s);
	int sdscmp(const sds s1, const sds s2);
	sds *sdssplitlen(const char *s, int len, const char *sep, int seplen, int *count);
	void sdsfreesplitres(sds *tokens, int count);
	void sdstolower(sds s);
	void sdstoupper(sds s);
	sds sdsfromlonglong(long long value);
	sds sdscatrepr(sds s, const char *p, size_t len);
	sds *sdssplitargs(const char *line, int *argc);
	sds sdsmapchars(sds s, const char *from, const char *to, size_t setlen);
	sds sdsjoin(char **argv, int argc, char *sep);
	
	// Low level functions exposed to the user API
	sds sdsMakeRoomFor(sds s, size_t addlen);
	void sdsIncrLen(sds s, int incr);
	sds sdsRemoveFreeSpace(sds s);
	size_t sdsAllocSize(sds s);
	
	*/

	public static void main(String[] args) {
		char[] tmp = "hello,world".toCharArray();
		SDS newstring = SDS.sdsnewlen(tmp, 11);
		System.out.println(Arrays.toString(newstring.buf));

		SDS voidsds = SDS.sdsempty();
		System.out.println(Arrays.toString(voidsds.buf));

		SDS s = SDS.sdsnew("cat".toCharArray());
		SDS cat = SDS.sdscat(s, tmp);
		System.out.println(Arrays.toString(cat.buf));

	}
}
