# I apologize for the bad code, this is my first experience on Python

import vk_api

# inter your data
login = "test"
password = "test"
id = 123456

vk_session = vk_api.VkApi(login, password)
vk_session.auth()
api = vk_session.get_api()

my_activitys = []
result = []

my_groups = api.groups.get(user_id=id, extended=True, fields='activity')
for i in my_groups['items']:
    my_activitys.append(i['activity'])

my_friends = api.friends.get(user_id=id)
for i in my_friends['items']:
    try:
        friend_groups = api.groups.get(user_id=i, extended=True, fields='activity')
        for j in friend_groups['items']:
            if j['activity'] in my_activitys:
                result.append(i)
                break
    except:
        pass

print(result)
