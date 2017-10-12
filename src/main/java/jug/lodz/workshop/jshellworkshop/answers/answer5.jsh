long[] start=new long[]{1,1}

LongStream fibo(long limit){
	return Stream.iterate(start,p->p[1]<limit,p->new long[]{p[1],p[0]+p[1]}).mapToLong(p->p[1]);
}