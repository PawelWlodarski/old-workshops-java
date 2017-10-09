IntStream createIntStream() {
return IntStream.range(1,20).filter(i->i%2==0);
}
