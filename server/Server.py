
from flask import Flask, render_template, request
import json
import sqlite3 as sql
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
            cursor = conn.execute("SELECT fb_friends from USER where fb_id='"+my_id+"'")
            friend_list = cursor[0][0].split(",")
            res['hope_list'] = []
            for friend_id in friend_list:
                cursor = conn.execute("SELECT * from HOPE where hope_whose_fb_id='"+friend_id+"'")
                for row in cursor:
                    one_hope = {}
                    one_hope['hope_id'] = row[0]
                    one_hope['hope_whose'] = row[1]
                    one_hope['hope_whose_id'] = row[2]
                    one_hope['hope_satisfied_by'] = row[3]
                    one_hope['hope_satisfied_by_id'] = row[4]
                    one_hope['hope_upload_time'] = row[5]
                    one_hope['hope_satisfied_time'] = row[6]
                    one_hope['gift_name'] = row[7]
                    one_hope['gift_content'] = row[8]
                    res['hope_list'].append(one_hope)
            pass
        if mth == 'get_target_hope':
            pass
        if mth == 'get_detail_hope':
            pass
        if mth == 'add_user':
            cur = conn.cursor()
            cur.execute('INSERT INTO USER (fb_id, fb_friends, fb_name) VALUES (?,?,?)', \
                (my_id, content['my_friend_list'], content['my_name']))
            conn.commit()
        if mth == 'add_hope':
            cur = conn.cursor()
            ts = time.time()
            st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d:%H:%M:%S')
            cur.execute('INSERT INTO HOPE (hope_whose_fb_id, hope_satisfied_by_fb_id, hope_upload_time, hope_satisfied_time, gift_name, gift_content) VALUES (?,?,?,?,?,?)', \
                (my_id, -1, st, '', content['gift_name'], content['gift_content']))
            newid = cur.lastrowid
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
            conn.commit()
        if mth == 'give_action':
            cur = conn.cursor()
            cur.execute('SELECT hope_whose_fb_id FROM HOPE where hope_uq_id like %s' % content['hope_id'])
            toId = cur.fetchall()[0]
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
        return json.dumps(res)

if __name__ == '__main__':
    app.run("0.0.0.0", port=4000, debug = True)
