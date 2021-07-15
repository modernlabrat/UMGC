import logging

werkzeug_log = logging.getLogger('werkzeug')
werkzeug_log.setLevel(logging.ERROR)

logging.basicConfig(filename='app.log', filemode='w', format='%(message)s')

def append_log(log):
    logging.error(log)
