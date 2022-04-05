from logic.line_finder import LineFinder
# TODO test how the GUI looks
class FileReader:
    name_to_data = {}

    def __init__(self, file_path):
        lines = LineFinder.get_lines(file_path)

        for line in lines:
            try:
                delimeter_start = line.index(":")
            except:
                print("NAH")

            name = line[:delimeter_start]
            data = line[delimeter_start + 1:]
            print(name)

            self.name_to_data[name] = data
    
    def get_number_list(self, item_name):
        """returns: List of Double; the list that has the line as the key"""

        data = self.name_to_data[item_name]

        # The first and last index don't matter
        current_item = ""
        number_list = []
        for ch in data[1:-1]:
            if ch == ",":
                number_list.append(float(current_item))
                current_item = ""
            
            else:
                current_item += ch
        return number_list
    
    def get_double(self, item_name):
        return self.name_to_data[item_name]

    def get_int(self, item_name):
        return int(self.get_double(item_name))


