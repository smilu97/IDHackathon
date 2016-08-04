
from flask import Flask, render_template, request
import json
import sqlite3 as sql
import time, datetime
app = Flask(__name__)

@app.route('/test', methods = ["POST"])
def test():
    content = request.get_json(silent=True)
    print content
    return '{"test":"success"}'

@app.route('/method', methods= ["POST"])
def method():
    content = request.get_json(silent=True)
    print content
    my_id = content["my_id"]
    mth = content["method"]
    print mth
    conn = sql.connect('sosoham.db')
    res = {}
    try:
        print "Opened database successfully";
        if mth == 'get_main_hope':
            res['hope_list'] = []
            print "myid : " + my_id
            cursor = conn.execute("SELECT fb_friends from USER where fb_id = "+my_id)
            friend_list = cursor.fetchall()[0][0].split(",")
            print friend_list
            for friend_id in friend_list:
                cursor = conn.execute("SELECT * from HOPE where hope_whose_fb_id = "+friend_id)
                for row in cursor:
                    print str(row)
                    one_hope = {}
                    one_hope['hope_id'] = row[0]
                    one_hope['hope_whose_id'] = row[1]
                    one_hope['hope_satisfied_by_id'] = row[2]
                    one_hope['hope_upload_time'] = row[3]
                    one_hope['hope_satisfied_time'] = row[4]
                    one_hope['gift_name'] = row[5]
                    one_hope['gift_content'] = row[6]
                    res['hope_list'].append(one_hope)
        if mth == 'get_target_hope':
            res['hope_list'] = []
            cursor = conn.execute("SELECT * from HOPE where hope_whose_fb_id like "+content["target_id"])
            for row in cursor:
                one_hope = {}
                one_hope['hope_id'] = row[0]
                one_hope['hope_whose_id'] = row[1]
                one_hope['hope_satisfied_by_id'] = row[2]
                one_hope['hope_upload_time'] = row[3]
                one_hope['hope_satisfied_time'] = row[4]
                one_hope['gift_name'] = row[5]
                one_hope['gift_content'] = row[6]
                res['hope_list'].append(one_hope)
        if mth == 'get_detail_hope':
            res['hope_list'] = []
            cursor = conn.execute("SELECT * from HOPE where hope_uq_id like "+content["hope_id"])
            for row in cursor:
                print str(row)
                one_hope = {}
                one_hope['hope_id'] = row[0]
                one_hope['hope_whose_id'] = row[1]
                one_hope['hope_satisfied_by_id'] = row[2]
                one_hope['hope_upload_time'] = row[3]
                one_hope['hope_satisfied_time'] = row[4]
                one_hope['gift_name'] = row[5]
                one_hope['gift_content'] = row[6]
                one_hope['action_list'] = []
                cur = conn.execute("SELECT * from ACTION where action_hope_id like "+content["hope_id"])
                for ac in cur:
                    one_ac = {}
                    one_ac['action_whose_id'] = ac[3]
                    one_ac['action_whose_name'] = ac[3]
                    one_ac['action_type'] = ac[1]
                    one_hope['action_list'].append(one_ac)
                res['hope_list'].append(one_hope)
        if mth == 'add_user':
            cur = conn.cursor()
            cur.execute('INSERT INTO USER (fb_id, fb_friends, fb_name) VALUES (?,?,?)', \
                (my_id, content['my_friend_list'], content['my_name']))
            conn.commit()
        if mth == 'add_hope':
            cur = conn.cursor()
            ts = time.time()
            st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d:%H:%M:%S')
            print st
            cur.execute('INSERT INTO HOPE (hope_whose_fb_id, hope_satisfied_by_fb_id, hope_upload_time, hope_satisfied_time, gift_name, gift_content) VALUES (?,?,?,?,?,?)', \
                (my_id, -1, st, '', content['gift_name'], content['gift_content']))
            newid = cur.lastrowid
            conn.commit()
            cur.execute('SELECT fb_name FROM USER where user_uq_id like %s' % my_id)
            hopeWhose = cur.fetchall()[0]
            res['hope_whose'] = hopeWhose
            res['hope_whose_id'] = my_id
            res['hope_satisfied_by'] = ''
            res['hope_satisfied_by_id'] = -1
            res['hope_id'] = newid
            res['hope_upload_time'] = st
            res['hope_satisfied_time'] = ''
            res['gift_name'] = content['gift_name']
            res['gift_content'] = content['gift_content']
            res['action_list'] = []
        if mth == 'give_action':
            cur = conn.cursor()
            cur.execute('SELECT hope_whose_fb_id FROM HOPE where hope_uq_id like %s' % content['hope_id'])
            toId = cur.fetchall()[0][0]
            print toId
            print "shit!!"
            ts = time.time()
            st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d:%H:%M:%S')
            cur.execute('INSERT INTO ACTION (action_type, action_from_fb_id, action_to_fb_id, action_hope_id, action_time) VALUES (?,?,?,?,?)', \
                (content['action_type'], content['my_id'], toId, content['hope_id'], st))
            conn.commit()
        if mth == 'give_hope':
            cur = conn.cursor()
            ts = time.time()
            st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d:%H:%M:%S')
            cur.execute('UPDATE HOPE SET hope_satisfied_by_fb_id = %s, hope_satisfied_time = %s where hope_uq_id like %s' % (my_id, st, content['hope_id']))
            conn.commit()
    except:
        conn.rollback()
    finally:
        conn.close()
        print "result!!"
        print res
        return json.dumps(res)

if __name__ == '__main__':
    app.run("0.0.0.0", port=4000, debug = True)
