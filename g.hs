main = do 	
		putStrLn "Hello. Let count how many people will have good results with JPP course. How many people is in group?"
		z <- readLn
          	if z == 159
              		then putStrLn "You're right!"
              		else putStrLn "You're wrong! It is 159 " 
		putStrLn "How many people was on lecture?" 
          	z <- readLn
		if z == 0 then putStrLn "O no! All of us were on JPP lectures all time"
              	else if z <= 159/2 then putStrLn "You're wrong! There was more people "
		else putStrLn "You're right! All of us were on JPP lectures "
		putStrLn "How many people was on your office hours during this semester?" 
          	z <- readLn
		if z == 0 then putStrLn "Okey. I agree with you we have some problems with this..."
              	else  putStrLn "Really good result :) "
		putStrLn "How many people can have good results with JPP course, you think? " 
		z <- readLn
		if z == 159 then putStrLn "Okey. Really good result :)"
		else if z>159 then putStrLn "Okey. But it is bigger than count of all students  "
              	else putStrLn "Wait. I’m thinking about it "
		putStrLn "Let count with me one more time  "
		let 	
				a = fibbonaci 12
				d = factorial 3
				f = ack 0 1
				res = d*f+a
		putStr "Well. The 12 is good number to start -> the fibonacci number of 12 is "
		print a 
		putStrLn ""
		putStr "Also we know that factorial of 3 is "	
		print d 
		putStrLn ""	
		putStr "And also there is an Ackermann function which with argument of 2 equals "	
		print f 
		putStr "After simple operations: " 
		putStr . show $ a
		putStr " + (" 
		putStr . show $  d 
		putStr " * " 
		putStr . show $  f 
		putStrLn ")" 
		putStr "We have a result =  "
		print res
		putStrLn "So the number we had is the number of good marks on JPP course " 
		putStrLn "It is only a joke. P.S. When I have done this project I fall in love with Haskell and started thinking about my final project in Haskell!  "

factorial 0 = 1  
factorial n = n * factorial (n - 1)  
	  
fibbonaci 0 = 0  
fibbonaci 1 = 1  
fibbonaci n = fibbonaci (n - 1) + fibbonaci (n - 2)  

ack 0 n = n+1
ack m 0 = ack (m-1) 1
ack m n = ack (m-1) (ack m (n-1))


	
			
