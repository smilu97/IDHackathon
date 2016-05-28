from flask import Flask, render_template, request
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
    my_id = content['my_id']
    mth = content['method']
    print mth
    if mth == 'get_main_hope':
        pass
    if mth == 'get_target_hope':
        pass
    if mth == 'get_detail_hope':
        pass
    if mth == 'add_user':
        pass
    if mth == 'add_hope':
        pass
    if mth == 'give_action':
        pass
    if mth == 'give_hope':
        pass
    

if __name__ == '__main__':
    app.run("0.0.0.0", port=4000, debug = True)
