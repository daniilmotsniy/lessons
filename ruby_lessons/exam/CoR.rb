class Handler

  def next_handler=(handler)
    raise NotImplementedError, "#{self.class} has not implemented method '#{__method__}'"
  end

  def handle(request)
    raise NotImplementedError, "#{self.class} has not implemented method '#{__method__}'"
  end
end

class AbstractHandler < Handler
  attr_writer :next_handler

  def next_handler(handler)
    @next_handler = handler
    handler
  end
  
  def handle(request)
    return @next_handler.handle(request) if @next_handler
    nil
  end
end

class AuthenticationHandler < AbstractHandler
  def handle(request)
    if request == 'test_pwd'
      "Pass is correct."
    else
      super(request)
    end
  end
end

class AuthorizationHandler < AbstractHandler
  def handle(request)
    if request == 'admin'
      "Admin roots."
    else
      super(request)
    end
  end
end

class AppHandler < AbstractHandler
  def handle(request)
    if request == 'app'
      "App selected."
    else
      super(request)
    end
  end
end

def client_code(handler)
  ['admin', 'app', 'test_pwd'].each do |auth|
    puts "\nRequest data: #{auth}"
    result = handler.handle(auth)
    if result
      print "  #{result}"
    else
      print "  #{auth} was skipped."
    end
  end
end

authentication = AuthenticationHandler.new
authorization = AuthorizationHandler.new
app = AppHandler.new

app.next_handler(authorization).next_handler(authentication)


puts 'Chain: App > Authentication > Authorization'
client_code(app)
puts "\n\n"

puts 'Subchain: Authentication > Authorization'
client_code(authentication)
