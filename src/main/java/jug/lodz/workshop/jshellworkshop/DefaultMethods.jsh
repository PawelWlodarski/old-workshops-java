public interface DefaultMethods1<A> {

    Integer app(A a);


    default DefaultMethods1<A> andThen(IntUnaryOperator g){
        return  a -> g.applyAsInt(this.app(a));
    }
}

interface DefaultMethods2<A> {

    Integer app(A a);


    default DefaultMethods2<A> multiplyBy(int howMany){
        return this.andThen(i->i*howMany);
    }

    default DefaultMethods2<A> thenAdd(int howMany){
        return this.andThen(i->i+howMany);
    }

    private DefaultMethods2<A> andThen(IntUnaryOperator g){
        return  a -> g.applyAsInt(this.app(a));
    }

}