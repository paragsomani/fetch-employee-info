
# fetch-employee-info
Sample output:

Result using completable futures: {Pune={Senior Recruiter=14000.0, Tech Lead=15000.0, Software Engineer=10000.0, Recruiter=14000.0}, Bangalore={Tech Lead=20000.0, Software Engineer=12666.666666666666, Recruiter=16000.0}}
 Time taken: 26ms
Result using parallel stream: {Pune={Senior Recruiter=14000.0, Tech Lead=15000.0, Software Engineer=10000.0, Recruiter=14000.0}, Bangalore={Tech Lead=20000.0, Software Engineer=12666.666666666666, Recruiter=16000.0}}
 Time taken: 6ms
Office Location: Pune
  Senior Recruiter: INR 14000.00
  Tech Lead: INR 15000.00
  Software Engineer: INR 10000.00
  Recruiter: INR 14000.00

Office Location: Bangalore
  Tech Lead: INR 20000.00
  Software Engineer: INR 12666.67
  Recruiter: INR 16000.00

__________________________________________________________
Using Parallel Stream:
__________________________________________________________
Office Location: Pune
  Senior Recruiter: INR 14000.00
  Tech Lead: INR 15000.00
  Software Engineer: INR 10000.00
  Recruiter: INR 14000.00

Office Location: Bangalore
  Tech Lead: INR 20000.00
  Software Engineer: INR 12666.67
  Recruiter: INR 16000.00


Process finished with exit code 0

