 void showTry() throws IOException{
   final BufferedReader br = new BufferedReader(new FileReader("pom.xml"));
   	try(br){
   		prn.accept(br.readLine());
    }
   }
