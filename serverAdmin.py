import json
from firebase import firebase
option1=0
option2=0
option3=0
option4=0
option5=0
def find_max():
    maxval = max(option1,option2,option3,option4,option5)
    if(option1 == maxval):
        return 1
    if(option2 == maxval):
        return 2
    if(option3 == maxval):
        return 3
    if(option4 == maxval):
        return 4
    if(option5 == maxval):
        return 5
    
firebase = firebase.FirebaseApplication('https://votingapp-9b3ad.firebaseio.com', None)
result = firebase.get('/', None)
values = result['User'].values()
dictionary = {}
for value in values:
    dictionary[value['username']] = value['option']
option1=0
option2=0
option3=0
option4=0
option5 = 0
list1 = []
for key,val in dictionary.items():
    if(val == 1):
        option1+=1
    if(val == 2):
        option2+=1
    if(val == 3):
        option3+=1
    if(val == 4):
        option4+=1
    if(val == 5):
        option5+=1
        
option = find_max()
print("Number of Votes for option 1:",option1)
print("Number of Votes for option 2:",option2)
print("Number of Votes for option 3:",option3)
print("Number of Votes for option 4:",option4)
print("Number of Votes for option 5:",option5)
print("Winner option:",option)

    
